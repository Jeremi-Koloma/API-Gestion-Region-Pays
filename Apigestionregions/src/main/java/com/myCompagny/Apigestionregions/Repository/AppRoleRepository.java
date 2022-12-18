package com.myCompagny.Apigestionregions.Repository;

import com.myCompagny.Apigestionregions.Modele.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

// Cette classe va étendre de l'interface JPA Repository avec paramètre <Entiy, Lond>
public interface AppRoleRepository extends JpaRepository <AppRole, Long> {
    // Une méthode qui permet de retourner les roles avec le nom du role pour authentification  ;
    AppRole findByRoleName (String roleName);
}
