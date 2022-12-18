package com.myCompagny.Apigestionregions.Modele;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity // Déclarons la classe comme une Entity JPA
@Getter // Génération des Getters
@Setter // Génération des Setters
@AllArgsConstructor // Un constructeur avec tous les paramètres
@NoArgsConstructor // Un constructeur sans paramètre
public class AppUser {
    @Id // Identifier cette proriété comme notre id;
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Générer notre clé primaire
    private Long id;

    private String username;
    private String password;

    @ManyToMany(fetch = FetchType.EAGER) // un user peut avoir plusieurs rôles, et un rôle concerne plusieurs users
    private Collection<AppRole> appRoles = new ArrayList<>();
}
