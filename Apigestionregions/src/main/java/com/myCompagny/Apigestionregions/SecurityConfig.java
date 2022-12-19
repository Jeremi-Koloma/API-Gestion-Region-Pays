package com.myCompagny.Apigestionregions;

import com.myCompagny.Apigestionregions.Filters.JwtAuthorizationFilter;
import com.myCompagny.Apigestionregions.Filters.jwtAuthenticationFilter;
import com.myCompagny.Apigestionregions.Modele.AppUser;
import com.myCompagny.Apigestionregions.Service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.ArrayList;
import java.util.Collection;

// Cette clase va étendre de WebSecurityConfigurerAdapter pour gérér nos chain de filtre
@Configuration // Dit qu'il s'agit d'une classe de configuration
@EnableWebSecurity // Permet à Spring de savoir où se trouve la classe de configuraion
@AllArgsConstructor // Pour l'injection de notre interface Account
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    // Cette classe a besion de 02 méthodes pour fonctionner
    // Injectons notre InterfaceService Account pour bénéficier de la méthode qui va nous permettre de charger un user par son username;
    private AccountService accountService;

    // La prémière méthode consiste à utiliser les identifients des utilisateurs vennant dans la base de donnée
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                // Quand l'user saisi son username et password, on le récupère dans la base de donnée.
                AppUser appUser = accountService.loadUserByUsername(username);
                // on retourne un object new User de Spring
                // Convertir la collection de AppRole vers une collection de GrantedAuthority
                Collection<GrantedAuthority> authorities = new ArrayList<>();
                // parcourir les rôles
                appUser.getAppRoles().forEach(r->{
                    authorities.add(new SimpleGrantedAuthority(r.getRoleName()));
                });
                return new User(appUser.getUsername(), appUser.getPassword(),authorities);
            }
        });
    }

    // La deuxième méthode consiste à prendre toutes les requêtes HTTP entrantes et sortante de votre application ainsi la gestion des Droits
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Ici on spécifie les Droits d'accès des requêtes

        //Désactiver le token csrf générer Spring sécurity pour qu'il laisse passer les requêtes
        http.csrf().disable();
        // Utiliser maintent la notion de StateLess
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        // Authoriser le refreshToken à passer
        http.authorizeRequests().antMatchers("/RegionUsers/refreshToken/**" ,"/login/**").permitAll();
        // Gestion des Droits
        http.authorizeRequests().antMatchers(HttpMethod.POST, "/RegionUsers/addUser/**").hasAuthority("ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.POST, "/RegionUsers/users/**").hasAuthority("USER");
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/RegionUsers/profile/**").hasAuthority("USER");
        // toutes les requêtes doivent être identifiées
        http.authorizeRequests().anyRequest().authenticated();
        // Ajoutons les filtres venant de la classe jwtAuthenticationFilter
        http.addFilter(new jwtAuthenticationFilter(authenticationManagerBean()));
        // Ajoutons le filtre venant de la classe jwtAuthorization
        http.addFilterBefore(new JwtAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    // WebSecurityConfigurerAdapter a une méthode authenticationManagerBean
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
