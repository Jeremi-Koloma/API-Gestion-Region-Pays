package com.myCompagny.Apigestionregions.Service;

import com.myCompagny.Apigestionregions.Modele.AppRole;
import com.myCompagny.Apigestionregions.Modele.AppUser;

import java.util.List;

public interface AccountService  {
    // On déclare ici les méthodes qu'on aura besoin dans l'application

    // une méthode qui permet d'ajouter des utilisateurs dans la base de donnée;
    AppUser addNewUser (AppUser appUser);

    // une méthode qui permet d'ajouter les rôles dans la base de donnée
    AppRole addNewRole (AppRole appRole);

    // Une méthode de type void qui permet d'affecter un rôle à un user
    void addRoleToUser (String userName, String roleName);

    // une méthode qui permet de charger un user pas son Nom;
    AppUser loadUserByUsername (String userName);

    // Une méthode qui permet de retourner une liste des utilisateurs
    List<AppUser> listUsers();
}
