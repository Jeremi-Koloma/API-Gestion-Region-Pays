package com.myCompagny.Apigestionregions.Controller;

import com.myCompagny.Apigestionregions.Modele.AppUser;
import com.myCompagny.Apigestionregions.Service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
