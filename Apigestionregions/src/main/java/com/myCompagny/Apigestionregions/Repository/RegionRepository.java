package com.myCompagny.Apigestionregions.Repository;

import com.myCompagny.Apigestionregions.Modele.Region;
import org.springframework.data.jpa.repository.JpaRepository;
// Cette interface va etendre l'Interface JpaRepository; avec param Entity(Region, et Id);
public interface RegionRepository extends JpaRepository<Region, Long> {

}
