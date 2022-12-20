package com.myCompagny.Apigestionregions.Controller;

import com.myCompagny.Apigestionregions.Modele.Pays;
import com.myCompagny.Apigestionregions.Service.PaysService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Identifier cette classe comme étant un Controller;
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("pays")
@AllArgsConstructor // Pour l'injection de notre interface PaysService;
@Api(value = "hello", description = "Les fonctionnalités de mon API de Tourisme : PAYS ") // Swagger;
public class PaysController {
    // Injecter notre interface Payservice;
    private final PaysService paysService; // ici c'est inversion de contrôle, parcek on passe par l'Interface pour implemter les méthode de PaysServiceImpl

    // Service pour la Méthode qui va crée le pays;
    @ApiOperation(value = "Ajouter un Pays") // Swagger;
    @PostMapping("/create") // Dit au contrôller qu'il sagit d'une Requête (POST) pour le Mapper;
    public Pays create(@RequestBody Pays pays) { // Envoyer les donnée body de la requête;
        return paysService.creer(pays);
    }

    // Service pour la Méthode pour lister pays;
    @ApiOperation(value = "Lister les Pays") // Swagger;
    @GetMapping("/read") // la requête (GET) pour READ;
    public List<Pays> read(){
        return paysService.lire();
    }

    //Service pour la Méthode modifier un pays;
    @ApiOperation(value = "Modifier un Pays") // Swagger;
    @PutMapping("/update/{idPays}") // La requête (PUT) avec param(idPays)
    public Pays update (@PathVariable Long idPays, @RequestBody Pays pays){
        return paysService.modifier(idPays, pays);
    }

    // Service pour la méthode Supprimé un pays;
    @ApiOperation(value = "Supprimer un Pays") // Swagger;
    @DeleteMapping("/delete/{idPays}") // La requête (DELETE) idPays;
    public String delete(@PathVariable Long idPays){
        return paysService.supprimer(idPays); // Qui va retourné le service supprimer;
    }

}
