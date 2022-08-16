package com.myCompagny.Apigestionregions.Repository;

import com.myCompagny.Apigestionregions.Modele.Pays;
import org.springframework.data.jpa.repository.JpaRepository;
// Cette interface va etendre l'Interface JpaRepository; avec param Entity(Pays, et idPays);
public interface PaysRepository extends JpaRepository<Pays, Long> {

}
