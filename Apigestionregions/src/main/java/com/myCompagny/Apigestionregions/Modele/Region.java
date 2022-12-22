package com.myCompagny.Apigestionregions.Modele;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity // Pour pouvoir la mapper avec une table au niveau de la base de donnée;
@Table(name = "REGIONS") // pour la donnée un Nom;
@Getter // Génère mes getters;
@Setter // Génère mes settes;
@NoArgsConstructor // constructeur sans arguments;
public class Region {
    @Id // Dire que c'est un id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Générer comme clé primaire et l'incrementation;
    private Long idRegion;

    @Column(length = 20)
    private String codeRegion;

    @Column(length = 50)
    private  String nomRegion;

    @Column(length = 50)
    private String activiteRegion;

    @Column(length = 50)
    private String superficieRegion;

    @Column(length = 20)
    private String langueParler;

    private String image;

    @ManyToOne // Cardinalité entre Pays et Region;
    @JoinColumn(name = "idPays") //Region (l'entité propriétaire) a une colonne de jointure idPays  qui stocke la valeur idPays et a une clé étrangère vers l' entité Pays
    private Pays pays;


}
