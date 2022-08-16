package com.myCompagny.Apigestionregions.Modele;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity // Pour que la classe puisse être mapper avec une table au niveau de la base de donnée;
@Table(name = "PAYS")  // on donne le nom de la table;
@Getter // Générer les getters;
@Setter // Générer les setters;
@NoArgsConstructor // Générer un constructeur sans Arguments;
public class Pays {
    @Id // Pour mapper l'identifiant;
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Générer comme une clé primaire and Auto-Incrimente;
    private Long idPays; // Pour l'identifiant de la Table;

    @Column(length = 8) // nombre de caractère au niveau de la base de donnée (VARCHAR);
    private String codePays;

    @Column(length = 25)
    private String nomPays;

    @JsonIgnore
    @OneToMany(mappedBy ="pays")
    private List<Region> regions;
}
