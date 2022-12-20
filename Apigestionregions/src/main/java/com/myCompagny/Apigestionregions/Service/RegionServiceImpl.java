package com.myCompagny.Apigestionregions.Service;

import com.myCompagny.Apigestionregions.Modele.Region;
import com.myCompagny.Apigestionregions.Repository.RegionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // Identifier cette classe comme étant du code métier (Services);
@AllArgsConstructor // Créons un constructeur avec tous les champs;
// Cette classe va implementer l'interface RegionService;
public class RegionServiceImpl implements RegionService {
    // Faire une injection par constructeur, Injectons notre RegionRepository;
    private final RegionRepository regionRepository;
    // On implemente les méthodes;
    @Override
    public Region creer(Region region) {
        return regionRepository.save(region); // Appélons la méthode Save pour la persitence des donnés, l'Enregistrement dans la base de donnée
    }
    // Lire méthode implémenté;
    @Override
    public List<Region> lire() {
        return regionRepository.findAll(); // Appélons la méthode (findAll) pour lire;
    }

    @Override
    public Region recupererUneRegion(Long id) {
        return regionRepository.findById(id).get();
    }

    // Modifier méthode implémenté;
    @Override
    public Region modifier(Long idRegion, Region region) {
        return regionRepository.findById(idRegion) // S'il trouve l'ID;
                .map(r-> { // On fait du mappage;
                    r.setCodeRegion(region.getCodeRegion()); // Modifier codeRegion;
                    r.setNomRegion(region.getNomRegion()); // Modifier nomRegion;
                    r.setActiviteRegion(region.getActiviteRegion()); // Modifier l'activiteRegion;
                    r.setSuperficieRegion(region.getSuperficieRegion()); // Modifier superficieRegion;
                    r.setLangueParler(region.getLangueParler()); // Modifier langParler;
                    return regionRepository.save(r); // retoune le resultat sauvegarder;
                }).orElseThrow(()-> new RuntimeException("Region non trouvé !")); // Message d'Exception;
    }
    // supprimer méthode implémenté;
    @Override
    public String supprimer(Long idRegion) {
        regionRepository.deleteById(idRegion); // Supprimer par l'ID
        return "Region Supprimé !";
    }
}
