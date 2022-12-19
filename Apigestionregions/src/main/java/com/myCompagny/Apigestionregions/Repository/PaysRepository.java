package com.myCompagny.Apigestionregions.Repository;

import com.myCompagny.Apigestionregions.Modele.Pays;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Cette interface va etendre l'Interface JpaRepository; avec param Entity(Pays, et idPays);
@Repository
public interface PaysRepository extends JpaRepository<Pays, Long> {

}
