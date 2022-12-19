package com.myCompagny.Apigestionregions.Filters;
// Cette classe va étendre de OncePerRequestFilter.

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.myCompagny.Apigestionregions.Controller.JWTUtil;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class JwtAuthorizationFilter extends OncePerRequestFilter {
    // Cette classe contient une seule méthode, cette méthode s'exécute à chaque requête qui arrive avec le token
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // Vérifie si la requête est égale à RefreshToken ou si c'est Login, tu passe
        if (request.getServletPath().equals("/RegionUsers/refreshToken") || request.getServletPath().equals("/login")){
            // Si c'est vrai alors il passe
            filterChain.doFilter(request, response);
        }
        else { // Sinon
            // Utiliser request pour lier avec le Header authorization
            String authorizationToken = request.getHeader(JWTUtil.AUTHORIZATION_HEADER);
            // Vérifier si authorizationToken n'est pas null et que ça commence avec Bearer
            if (authorizationToken!= null && authorizationToken.startsWith(JWTUtil.PREFIX_BEARER)){
                try {
                    String jwt = authorizationToken.substring(JWTUtil.PREFIX_BEARER.length()); // Ignorer les 7 premeier caractères
                    Algorithm algorithm = Algorithm.HMAC256(JWTUtil.SECRET); // La même clé secrête
                    JWTVerifier jwtVerifier = JWT.require(algorithm).build(); // Créer le verifier
                    DecodedJWT decodedJWT =  jwtVerifier.verify(jwt); // On vérifie si le jwt est valide avec les roles et le username
                    // Si tout va bien, on récupère le username
                    String uername = decodedJWT.getSubject();
                    // on Recupère les rôles qui sont dans un tableau de String
                    String[] roles = decodedJWT.getClaim("roles").asArray(String.class);
                    // Convertir les rôles en une liste de string
                    Collection<GrantedAuthority> authorities = new ArrayList<>();
                    for (String r:roles ){
                        authorities.add(new SimpleGrantedAuthority(r));
                    }
                    UsernamePasswordAuthenticationToken authenticationToken =
                            new UsernamePasswordAuthenticationToken(uername, null,authorities);
                    // Maintenant on s'authentifie le user
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    // Une fois s'authentifier, on passe au suivant
                    filterChain.doFilter(request, response);

                }catch (Exception e){
                    response.setHeader("error-message", e.getMessage());
                    response.sendError(HttpServletResponse.SC_FORBIDDEN);
                }

            }
            else {
                // Si la requête n'eccessite pas d'authentification, on laisse passé
                filterChain.doFilter(request, response);
            }
        }



    }

}
