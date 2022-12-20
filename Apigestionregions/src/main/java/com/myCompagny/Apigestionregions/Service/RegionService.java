package com.myCompagny.Apigestionregions.Service;

import com.myCompagny.Apigestionregions.Modele.Region;

import java.util.List;

public interface RegionService {
    // Ici on crée nos méthode;
    // Une méthode va retourné une Region (Modele); // methode CREATE
    Region creer(Region region);

    // Une méthode va retourné une liste de Region (Modele); // methode READ
    List<Region> lire();

    // Retourner une seule Region;
    Region recupererUneRegion(Long id);

    // Une méthode va retourné une Region (Modele), qui va prendre parm (idRegion, et la Region); // methode UPDATE
    Region modifier(Long idRegion, Region region);

    // Une méthode va retourné String qui va prendre en param (idRegion) à supprimer; // methode DELECTE
    String supprimer(Long idRegion);

}
