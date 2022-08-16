package com.myCompagny.Apigestionregions.Service;

import com.myCompagny.Apigestionregions.Modele.Populations;
import com.myCompagny.Apigestionregions.Repository.PopulationsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // Identifier cette classe comme étant du service métier (Servie);
@AllArgsConstructor // Un constructeur avec tous les param // Pour l'injections de PopulationsRepository;
// cette classe va implémenter l'interface PopulationsService;
// Après l'implémentation, on implémente les 04 méthodes de PopulationsService;
public class PopulationsServiceImpl implements PopulationsService {
    // Injectons PopulationsRepository;
    private final PopulationsRepository populationsRepository;

    // les méthodes implémentées;
    // Méthode Lire;
    @Override
    public Populations creer(Populations populations) {
        return populationsRepository.save(populations); // Appélons la méthode (SAVE) Entity, Pour la persistance des données dans la base de donnée;
    }

    // Méthode Lire;
    @Override
    public List<Populations> lire() {
        return populationsRepository.findAll(); // Appélons la méthode (findAll) pour lire nos données;
    }

    // Méthode modifier;
    @Override
    public Populations modifier(Long idPopulations, Populations populations) {
        return populationsRepository.findById(idPopulations) // Cherchons par Id;
                .map(p-> { // Si on trouve on fait le mapping;
                    p.setAnnee(populations.getAnnee()); // Modifier l'année de population;
                    p.setNombrPopulation(populations.getNombrPopulation()); // Modifier le nombre la population;
                    return populationsRepository.save(p); // Retourner le resultat pour savegarder;
                }).orElseThrow(()-> new RuntimeException("Population non trouvé !"));
    }

    // Méthode supprimer;
    @Override
    public String Supprimer(Long idPopulations) {
        return "Population Supprimer !";
    }
}
