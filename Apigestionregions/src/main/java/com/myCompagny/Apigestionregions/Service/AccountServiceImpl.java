package com.myCompagny.Apigestionregions.Service;

import com.myCompagny.Apigestionregions.Modele.AppRole;
import com.myCompagny.Apigestionregions.Modele.AppUser;
import com.myCompagny.Apigestionregions.Repository.AppRoleRepository;
import com.myCompagny.Apigestionregions.Repository.AppUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

// Cette classe va implémenter nos méthodes définies dans l'interface AccountService
@Service // Déclarons la classe comme une couche de service
@Transactional // Transaction de spring
@AllArgsConstructor // Pour l'injection des dépendances, nos Repositories
public class AccountServiceImpl implements AccountService{
    // On implémente les méthodes
    // Injectons nos deux Repositories
    private AppUserRepository appUserRepository;
    private AppRoleRepository appRoleRepository;
    // Injections PasswordEncoder
    private PasswordEncoder passwordEncoder;

    @Override // Ajouter un utilisateur
    public AppUser addNewUser(AppUser appUser) {
        // Encoder les mots de passe des users en les ajoutant
        // On récupère le password saisi par l'utilisateur
        String pw = appUser.getPassword();
        // Maintent on encode le password
        appUser.setPassword(passwordEncoder.encode(pw));
        return appUserRepository.save(appUser); // Pour la persistence des users dans la database
    }

    @Override // Ajouter un Rôle à un utilisateur
    public AppRole addNewRole(AppRole appRole) {
        return appRoleRepository.save(appRole); // Pour la persistance des rôles dans la database
    }

    @Override // Affecter un Rôle à un utilisateur
    public void addRoleToUser(String userName, String roleName) {
        // Pour l'affecter un rôle à un USER, on le récupère d'abord dans la base de donne;
        AppUser appUser = appUserRepository.findByUsername(userName);
        // On récupère aussi les roles dans la base de donnée
        AppRole appRole = appRoleRepository.findByRoleName(roleName);
        // Maintenant on l'affecte un role
        appUser.getAppRoles().add(appRole);
    }

    @Override // Pour charger un utilisateur
    public AppUser loadUserByUsername(String userName) { // on le charge par son Username;
        return appUserRepository.findByUsername(userName);
    }

    @Override
    public List<AppUser> listUsers() { // Afficher tous les users
        return appUserRepository.findAll();
    }
}
