package com.aquagrid.repository;

import com.aquagrid.model.WaterZone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WaterZoneRepository extends JpaRepository<WaterZone, Long> {

    boolean existsByZoneName(String zoneName);

    Optional<WaterZone> findByZoneName(String zoneName);

    List<WaterZone> findByCity(String city);
}