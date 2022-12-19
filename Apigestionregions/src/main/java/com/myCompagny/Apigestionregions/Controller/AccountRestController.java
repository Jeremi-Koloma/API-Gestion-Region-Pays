package com.myCompagny.Apigestionregions.Controller;

import com.myCompagny.Apigestionregions.Modele.AppRole;
import com.myCompagny.Apigestionregions.Modele.AppUser;
import com.myCompagny.Apigestionregions.Service.AccountService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("RegionUsers")
@AllArgsConstructor // Pour l'injection de des dépendances, notre Interface Service Account
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



}

// Créons une classe, les champs de l'admin va renseigner pour affecter un rôle à un user
@Getter
@Setter
class RoleUserForm{
    private String username;
    private String roleName;
}
