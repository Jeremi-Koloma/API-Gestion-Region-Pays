package com.myCompagny.Apigestionregions.Repository;

import com.myCompagny.Apigestionregions.Modele.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Cette classe va étendre de l'interface JPA Repositroy avec <Entity, Lond>
@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    //  Une méthode qui permet de retourner un User par son nom d'utilisateur pour authentification
    AppUser findByUsername (String username);
}
