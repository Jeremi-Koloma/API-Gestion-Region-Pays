package com.myCompagny.Apigestionregions.Controller;

import com.myCompagny.Apigestionregions.Modele.Region;
import com.myCompagny.Apigestionregions.Service.RegionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Identifier cette classe comme étant un Controller;
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials = "true")
@RequestMapping("/region") // Le path, ou le lien pour le Navigateur
@AllArgsConstructor // Pour Injectons notre RegionsService;
@Api(value = "hello", description = "Les fonctionnalités de mon API de Tourisme : REGION") // Swagger;
public class RegionController {
    // Injecter notre RegionsService;
    private final RegionService regionService; // ici c'est inversion de contrôle, parcek on passe par l'Interface pour implemter les méthode de ServiceImpl

    // Seervice pour la méthode qui va creer la Region;
    @ApiOperation(value = "Ajouter une Region") // Swagger;
    @PostMapping("/create") // Dit au contrôller qu'il sagit d'une Requête (POST) pour le Mapper;
    public Region create(@RequestBody Region region){ // Envoyer les donnée body de la requête;
        return regionService.creer(region); //Ça va appéler la méthode créer et Envoyé la region;
    }

    // Service pour la méthode qui va lire nos Regions;
    @ApiOperation(value = "Liste des Regions") // Swagger;
    @GetMapping("/read")
    public List<Region> read(){
        return regionService.lire(); // la requête (GET) pour READ;
    }

    @GetMapping("/uneregion/{id}")
    public Region uneRegion(@PathVariable Long id){
        return regionService.recupererUneRegion(id);
    }

    // Service pour la méthode Modifier;
    @ApiOperation(value = "Modifier une Region")
    @PutMapping("/update/{idRegion}") // La requête (PUT) avec param(idRegion)
    public Region update(@PathVariable Long idRegion, @RequestBody Region region){
        return regionService.modifier(idRegion, region); // Qui va retourné le service modifier;
    }

    //service la méthode Supprimer
    @ApiOperation(value = "Supprimer une Region") // Swagger;
    @DeleteMapping("/delete/{idRegion}") // La requête (DELETE) idRegion;
    public String delete(@PathVariable Long idRegion){
        return regionService.supprimer(idRegion); // Qui va retourné le service supprimer;
    }
}
