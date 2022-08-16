package com.myCompagny.Apigestionregions.Repository;

import com.myCompagny.Apigestionregions.Modele.Populations;
import org.springframework.data.jpa.repository.JpaRepository;

// cette interface va extendre JpaRepository; avec param <Entity, Long>

public interface PopulationsRepository extends JpaRepository<Populations, Long>  {

}
