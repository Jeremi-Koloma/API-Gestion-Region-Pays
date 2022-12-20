package com.myCompagny.Apigestionregions.Controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.myCompagny.Apigestionregions.Modele.AppRole;
import com.myCompagny.Apigestionregions.Modele.AppUser;
import com.myCompagny.Apigestionregions.Service.AccountService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("RegionUsers")
@AllArgsConstructor // Pour l'injection de des dépendances, notre Interface Service

public class AccountRestController {
    // Injectons notre serviceAccount
    private AccountService accountService;

    // une méthode qui va retourner la liste des utilisateurs
    @GetMapping("/users")
    public List<AppUser> appUsers(){
        return accountService.listUsers();
    }

    // Une méthode qui va ajouter un utilisateur
    @PostMapping("/addUser")
    public AppUser saveUser(@RequestBody AppUser appUser){ // RequestBody pour dire que les données de AppUser se trouve dans le corps de la requête
        return accountService.addNewUser(appUser);
    }

    // Une méthode qui va ajouter des Rôles
    @PostMapping("/addRole")
    public AppRole saveRole(@RequestBody AppRole appRole){ // RequestBody Pour dire que les données de AppRole se trouve dans le corps de la requête
        return accountService.addNewRole(appRole);
    }

    // Une méthode qui va permettre d'ajouter un rôle à un utilisateur
    @PostMapping("/addRoleToUser")
    public void addRoleToUser(@RequestBody RoleUserForm roleUserForm){ // RequestBody pour dire que les données se trouve dans le corps de la requête
          accountService.addRoleToUser(roleUserForm.getUsername(), roleUserForm.getRoleName());
    }

    // une méthode qui retoune un void pour le RefreshToken
    @GetMapping("/refreshToken")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws Exception{
        // Lier le Header au requête
        String authToken = request.getHeader(JWTUtil.AUTHORIZATION_HEADER);
        // Vérification si le token n'est pas null et que ça commence avec un Bearer
        if (authToken != null && authToken.startsWith(JWTUtil.PREFIX_BEARER)){
            try {
                String jwt = authToken.substring(JWTUtil.PREFIX_BEARER.length()); // Ignorer les 7 premeier caractères
                Algorithm algorithm = Algorithm.HMAC256(JWTUtil.SECRET); // La même clé secrête
                JWTVerifier jwtVerifier = JWT.require(algorithm).build(); // Créer le verifier
                DecodedJWT decodedJWT =  jwtVerifier.verify(jwt); // On vérifie si le jwt est valide avec les roles et le username
                // Si tout va bien, on récupère le username
                String uername = decodedJWT.getSubject();
                // Vérifier les blaclist
                AppUser appUser = accountService.loadUserByUsername(uername);
                // Générons le token
                String jwtAccessToken = JWT.create()
                        .withSubject(appUser.getUsername()) // username
                        .withExpiresAt(new Date(System.currentTimeMillis() + JWTUtil.EXPIRE_ACCESS_TOKEN)) // date d'expiration
                        .withIssuer(request.getRequestURL().toString()) // Le nom de l'application qui a généré le token
                        .withClaim("roles", appUser.getAppRoles().stream().map(r->r.getRoleName()).collect(Collectors.toList())) // convertir la liste des authority en lsite de string
                        .sign(algorithm); // On signe maintenant avec le même algorithme

                Map<String, String> idToken = new HashMap<>();
                idToken.put("access_Token", jwtAccessToken);
                idToken.put("refresh_Token", jwt);
                response.setContentType("application/json");
                // Maintenant envoi le jwt au client,dans le corps de la reponse en format JSON
                new ObjectMapper().writeValue(response.getOutputStream(),idToken);

            }catch (Exception e){
               throw e;
            }
        }else {
            throw  new RuntimeException("Le Refresh-Token est obligatoire !");
        }

    }

    // Le profile de user
    @GetMapping("/profile") // Injecter le Principal de Spring Security pour avoir accès au profile de user
    public  AppUser profile(Principal principal){
        return accountService.loadUserByUsername(principal.getName());
    }



}

// Créons une classe, les champs de l'admin va renseigner pour affecter un rôle à un user
@Getter
@Setter
class RoleUserForm{
    private String username;
    private String roleName;
}
