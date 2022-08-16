package com.myCompagny.Apigestionregions.Service;

import com.myCompagny.Apigestionregions.Modele.Pays;

import java.util.List;

public interface PaysService {
    // Ici on crée nos 04 méthodes;

    // Une méthode qui va retourner un pays, // Méthode CREATE;
    Pays creer(Pays pays);

    // Une méthode qui va retouner un pays // Méthode READ;
    List<Pays> lire();

    // Une méthode qui va retourner un pays et prendre en paramètre (idPays, le Pays) à modifier // Méthode UPDATE;
    Pays modifier(Long idPays, Pays pays);

    // Une méthode qui va retourner une String et prend en paramètre (idPays) à supprimer // Méthode DELETE;
    String supprimer(Long idPays);

}
