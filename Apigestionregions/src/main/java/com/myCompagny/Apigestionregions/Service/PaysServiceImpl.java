package com.myCompagny.Apigestionregions.Service;

import com.myCompagny.Apigestionregions.Modele.Pays;
import com.myCompagny.Apigestionregions.Repository.PaysRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // Identifier cette classe comme étant du service métier(Service);
@AllArgsConstructor // Un constructeur avec tous les champs; // Pour injections de notre PaysRepository;
// Cette classe va implémenté l'interface PaysService;
// Après la l'implémentation de l'interface, on implémente nos méthodes aussi;
public class PaysServiceImpl implements PaysService {
    // Les méthodes implémentées;

    private final PaysRepository paysRepository; // un constructeur par injection de notre PaysRepository;

    // méthode créer;
    @Override
    public Pays creer(Pays pays) {
        return paysRepository.save(pays); // Appélons la méthode (SAVE) pour la persistence des données (Base de donnée);
    }
    // Méthode lire;
    @Override
    public List<Pays> lire() {
        return paysRepository.findAll(); // Appélons la méthode (findAll) pour lire nos données;
    }
    // Méthode modifier;
    @Override
    public Pays modifier(Long idPays, Pays pays) {
        return paysRepository.findById(idPays) // Cherchons par idPays;
                .map(p-> { // Si on trouve on fait le mapping;
                    p.setCodePays(pays.getCodePays()); // Modifier le codePays;
                    p.setNomPays(pays.getNomPays()); // Modifier le nomPays;
                    return paysRepository.save(p); // retourner le resulat sauvegarder;
                }).orElseThrow(()-> new RuntimeException("Pays non trouvé !"));
    }
    // Méthode supprimer;
    @Override
    public String supprimer(Long idPays) {
        paysRepository.deleteById(idPays);
        return "Pays Supprimé !";
    }
}
