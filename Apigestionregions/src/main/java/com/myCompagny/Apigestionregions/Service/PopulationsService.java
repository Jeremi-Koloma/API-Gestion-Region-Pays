package com.myCompagny.Apigestionregions.Service;

import com.myCompagny.Apigestionregions.Modele.Populations;

import java.util.List;

public interface PopulationsService {
    // Ici on définie nos 04 méthode;

    // Une méthode qui va retourné Entity Population;
    Populations creer(Populations populations); // CREATE

    // une méthode qui va retouné une liste de Population;
    List<Populations> lire(); // READ;

    // une méthode qui va retourné un Population avec param (idPopulations, Populations) // UPDATE;
    Populations modifier(Long idPopulations, Populations populations);

    // Une méthode qui va retourné String et prend en param (idPoulations) à Supprimer;
    String Supprimer(Long idPopulations);
}
