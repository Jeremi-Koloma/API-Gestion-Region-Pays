package com.myCompagny.Apigestionregions.Modele;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity() // Identifier la classe comme Entity;
@Table(name = "POPULATIONS") // Nommer la table;
@NoArgsConstructor // Un constructeur sans Arguments;
@Getter // Générer nos getters;
@Setter // Générer les setters;
public class Populations {
    @Id // Identifier id de la classe;
    // Identifier comme clé primaire et Auto-incremante;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPopulations;

    @Column(length = 10)
    private String annee;

    @Column(length = 100)
    private String nombrPopulation;

    @ManyToOne // Cardinalité entre Region et Population;
    private Region region;
}
