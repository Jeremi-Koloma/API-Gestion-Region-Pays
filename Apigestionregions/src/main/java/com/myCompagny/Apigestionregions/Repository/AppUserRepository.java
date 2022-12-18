package com.myCompagny.Apigestionregions.Repository;

import com.myCompagny.Apigestionregions.Modele.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

// Cette classe va étendre de l'interface JPA Repositroy avec <Entity, Lond>
public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    //  Une méthode qui permet de retourner un User par son nom d'utilisateur pour authentification
    AppUser findByUsername (String userName);
}
