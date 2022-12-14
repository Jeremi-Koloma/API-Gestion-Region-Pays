package com.myCompagny.Apigestionregions.Filters;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.myCompagny.Apigestionregions.Controller.JWTUtil;
import com.myCompagny.Apigestionregions.Modele.AppUser;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

// cette classe va étender de UsernamePasswordAuthenticationFilter pour la génération du Token
@AllArgsConstructor // pour l'injection de AuthenticationManager
public class jwtAuthenticationFilter  extends UsernamePasswordAuthenticationFilter {
    // Cette classe à deux méthodes à utiliser
    // Ces deux filtres ont besoin de AuthenticationManager pour fonctionner
    private AuthenticationManager authenticationManager;

    // La prémière méthode quand l'utilisateur essaye de s'authentifier.
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        // Récupérons username et password saisi par l'utilisateur
        System.out.println("--------- Essai d'authentification ----------");
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(JsonParser.Feature.AUTO_CLOSE_SOURCE,true);
        AppUser appUser;
        try {
            appUser = objectMapper.readValue(request.getInputStream(),AppUser.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        // Essayement de voir les informations de user
        System.out.println(appUser.getUsername());
        System.out.println(appUser.getPassword());
        // quand on récupère username et password, on les stocke dans un object de Spring UsernamePasswordAuthenticationToken
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(appUser.getUsername(), appUser.getPassword());
        // ici on identifie le user
        return authenticationManager.authenticate(authenticationToken);
    }


    // La deuxième méthode quand l'authentification a reussi
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        System.out.println("--------- Authentification reussi ----------");
        // on retourne l'utilisateur authentifier
        User user = (User) authResult.getPrincipal();
        // N'oubliez pas d'importer la dépendance JWT

        // Le Header du token
        // Pour la signature du jwt choissons l'algorithme
        Algorithm algo1 =  Algorithm.HMAC256(JWTUtil.SECRET);
        // Générons le token
        String jwtAccessToken = JWT.create()
                .withSubject(user.getUsername()) // username
                        .withExpiresAt(new Date(System.currentTimeMillis()+JWTUtil.EXPIRE_ACCESS_TOKEN)) // date d'expiration
                        .withIssuer(request.getRequestURL().toString()) // Le nom de l'application qui a généré le token
                        .withClaim("roles", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList())) // convertir la liste des authority en lsite de string
                        .sign(algo1); // On signe maintenant avec le même algorithme

        // Le RefreshToken
        String jwtRefreshToken = JWT.create()
                .withSubject(user.getUsername()) // username
                .withExpiresAt(new Date(System.currentTimeMillis()+JWTUtil.EXPIRE_REFRESH_TOKEN)) // date d'expiration
                .withIssuer(request.getRequestURL().toString()) // Le nom de l'application qui a généré le token
                .sign(algo1); // On signe maintenant avec le même algorithme

        Map<String, String> idToken = new HashMap<>();
        idToken.put("access_Token", jwtAccessToken);
        idToken.put("refresh_Token", jwtRefreshToken);
        response.setContentType("application/json");
        // Maintenant envoi le jwt au client,dans le corps de la reponse en format JSON
        new ObjectMapper().writeValue(response.getOutputStream(),idToken);
    }
}
