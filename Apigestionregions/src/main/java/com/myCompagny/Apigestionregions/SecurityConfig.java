package com.myCompagny.Apigestionregions;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
// Cette clase va étendre de WebSecurityConfigurerAdapter pour gérér nos chain de filtre
@Configuration // Dit qu'il s'agit d'une classe de configuration
@EnableWebSecurity // Permet à Spring de savoir où se trouve la classe de configuraion
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    // Cette classe a besion de 02 méthodes pour fonctionner;

    // La prémière méthode consiste à utiliser les identifients des utilisateurs vennant dans la base de donnée
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

    }

    // La deuxième méthode consiste à prendre toutes les requêtes HTTP entrantes et sortante de votre application ainsi la gestion des Droits
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Ici on spécifie les Droits d'accès des requêtes

        //Désactiver le token csrf générer Spring sécurity pour qu'il laisse passer les requêtes
        http.csrf().disable();
        // Ouvrir la porte à toute les réquêtes.
        http.authorizeRequests().anyRequest().permitAll();
    }
}
