package com.aquagrid.repository;

import com.aquagrid.model.WaterZone;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WaterZoneRepository extends JpaRepository<WaterZone, Long> {

    boolean existsByZoneName(String zoneName);

    List<WaterZone> findByCity(String city);
}