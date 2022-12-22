package com.myCompagny.Apigestionregions.Repository;

import com.myCompagny.Apigestionregions.Modele.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Cette interface va etendre l'Interface JpaRepository; avec param Entity(Region, et Id);
@Repository
public interface RegionRepository extends JpaRepository<Region, Long> {
    // Conter le nombre des regions;

}
