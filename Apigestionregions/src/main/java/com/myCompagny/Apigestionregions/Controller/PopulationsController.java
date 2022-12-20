package com.myCompagny.Apigestionregions.Controller;

import com.myCompagny.Apigestionregions.Modele.Populations;
import com.myCompagny.Apigestionregions.Service.PopulationsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Identifier la classe comme étant un controller;
@RequestMapping("population") // le path ou non du lien dans le navigateur;
@CrossOrigin(origins = "http://localhost:4200")
@AllArgsConstructor // Un constructeur avec tous les arguments pour l'injection de l'interface PopulationsService;
@Api(value = "hello", description = "Les fonctionnalités de mon API de Tourisme : POPULATION ") // Swagger;
public class PopulationsController {
    //Injectons l'interface populationsService;
    private final PopulationsService populationsService;

    //Controller pour le service qui va créer la polutation;
    @ApiOperation(value = "Ajouter la Population") // Swagger;
    @PostMapping("/create") // Pour une requête de type POST;
    public Populations create(@RequestBody Populations populations){ // Envoyer les données de body de la requête;
        return populationsService.creer(populations);
    }

    //Controller pour le service qui va lire la population;
    @ApiOperation(value = "Lister la Population") // Swagger;
    @GetMapping("/read") // pour une requête de type GET;
    public List<Populations> read(){
        return populationsService.lire();
    }

    // Controller pour le service qui va modifier;
    @ApiOperation(value = "Modifier la Population") // Swagger;
    @PutMapping("/update/{idPopulation}") // Une requête de type PUT
    public Populations update(@PathVariable Long idPopulation, @RequestBody Populations populations){
        return populationsService.modifier(idPopulation, populations);
    }

    // Controller pour le service qui va supprimer;
    @ApiOperation(value = "Supprimer la Population") // Swagger;
    @DeleteMapping("/delete/{idPopulation}") // Une requête de type DELETE
    public String delete(@PathVariable Long idPopulations){
        return populationsService.Supprimer(idPopulations);
    }
}
