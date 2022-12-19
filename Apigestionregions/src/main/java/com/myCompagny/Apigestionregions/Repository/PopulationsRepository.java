package com.myCompagny.Apigestionregions.Repository;

import com.myCompagny.Apigestionregions.Modele.Populations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// cette interface va extendre JpaRepository; avec param <Entity, Long>
@Repository
public interface PopulationsRepository extends JpaRepository<Populations, Long>  {

}
