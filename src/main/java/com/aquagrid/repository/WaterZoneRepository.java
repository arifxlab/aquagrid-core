package com.aquagrid.repository;

import com.aquagrid.model.WaterZone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WaterZoneRepository extends JpaRepository<WaterZone, Long> {

    boolean existsByZoneName(String zoneName);
}