package com.myCompagny.Apigestionregions.Controller;

import com.myCompagny.Apigestionregions.Modele.Region;
import com.myCompagny.Apigestionregions.Repository.PaysRepository;
import com.myCompagny.Apigestionregions.Service.RegionService;
import com.myCompagny.Apigestionregions.images.ConfigImage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@RestController // Identifier cette classe comme étant un Controller;
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials = "true")
@RequestMapping("/region") // Le path, ou le lien pour le Navigateur
@AllArgsConstructor // Pour Injectons notre RegionsService;
@Api(value = "hello", description = "Les fonctionnalités de mon API de Tourisme : REGION") // Swagger;
public class RegionController {
    // Injecter notre RegionsService;
    private final RegionService regionService; // ici c'est inversion de contrôle, parcek on passe par l'Interface pour implemter les méthode de ServiceImpl

    // Injectons notre PaysRepository pour avoir accès au id du pays
    private final PaysRepository paysRepository;

    // Seervice pour la méthode qui va creer la Region;
    @ApiOperation(value = "Ajouter une Region sans image") // Swagger;
    @PostMapping("/create") // Dit au contrôller qu'il sagit d'une Requête (POST) pour le Mapper;
    public Region create(@RequestBody Region region){ // Envoyer les donnée body de la requête;
        return regionService.creer(region); //Ça va appéler la méthode créer et Envoyé la region;
    }

    // Ajouter une Région avec images
    @ApiOperation(value = "Ajouter une Region avec image") // Swagger;
    @PostMapping("/createAvecImage/{pays}") // Dit au contrôller qu'il sagit d'une Requête (POST) pour le Mapper;
    public Region createWithImage(@Param("file") MultipartFile file,
                                  @Param("codeRegion") String codeRegion,
                                  @Param("nomRegion") String nomRegion,
                                  @Param("activiteRegion") String activiteRegion,
                                  @Param("superficieRegion") String superficieRegion,
                                  @Param("langueParler") String langueParler,
                                  @PathVariable Long pays) throws IOException {

        // Créeons un Object Region pour lier les information du @param à Region qui va être ajouter
        Region region = new Region();

        // Récupérons le Nom source de l'image
        String nomfile = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));

        // Choisir un emplacement oû l'image sera stocker
        String url= "C:\\Users\\jkkoloma\\Downloads\\Front-Region\\src\\assets\\saveImages";

        // Maintenant on donne à notre classe ConfigImage url, le nomfile, et file.
        ConfigImage.saveimgA(url, nomfile, file);

        // Les données vennant de @param, on les prend et les ajouter à notre object region créer.
        region.setNomRegion(nomRegion);
        region.setCodeRegion(codeRegion);
        region.setActiviteRegion(activiteRegion);
        region.setLangueParler(langueParler);
        region.setSuperficieRegion(superficieRegion);
        region.setImage(nomfile);
        // Récupération du pays dans la database
        region.setPays(paysRepository.findById(pays).get());

        //return regionService.creer(region); //Ça va appéler la méthode créer et Envoyé la region;
        return regionService.creer(region);
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
