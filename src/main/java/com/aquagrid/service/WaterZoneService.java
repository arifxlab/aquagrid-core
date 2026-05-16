package com.aquagrid.service;

import com.aquagrid.exception.ResourceNotFoundException;
import com.aquagrid.model.WaterZone;
import com.aquagrid.model.ZoneStatus;
import com.aquagrid.repository.WaterZoneRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class WaterZoneService {

    private final WaterZoneRepository repository;

    // =========================
    // BUSINESS RULE ENGINE
    // =========================
    private ZoneStatus calculateStatus(Double waterLevel) {

        if (waterLevel == null) {
            return ZoneStatus.CRITICAL;
        }

        if (waterLevel <= 40) {
            return ZoneStatus.CRITICAL;
        } else if (waterLevel <= 70) {
            return ZoneStatus.WARNING;
        } else {
            return ZoneStatus.ACTIVE;
        }
    }

    // =========================
    // CREATE ZONE
    // =========================
    @Transactional
    public WaterZone createZone(WaterZone zone) {

        if (repository.existsByZoneName(zone.getZoneName())) {
            throw new RuntimeException("Zone already exists: " + zone.getZoneName());
        }

        // BUSINESS LOGIC ADDED (STEP 2)
        zone.setStatus(calculateStatus(zone.getWaterLevel()));

        return repository.save(zone);
    }

    // =========================
    // GET ALL
    // =========================
    @Transactional(readOnly = true)
    public List<WaterZone> getAllZones() {
        return repository.findAll();
    }

    // =========================
    // GET BY ID
    // =========================
    @Transactional(readOnly = true)
    public WaterZone getZoneById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Zone not found: " + id));
    }

    // =========================
    // UPDATE ZONE
    // =========================
    @Transactional
    public WaterZone updateZone(Long id, WaterZone updated) {

        WaterZone existing = getZoneById(id);

        existing.setZoneName(updated.getZoneName());
        existing.setCity(updated.getCity());
        existing.setWaterLevel(updated.getWaterLevel());

        // BUSINESS LOGIC ADDED (STEP 2)
        existing.setStatus(calculateStatus(updated.getWaterLevel()));

        return repository.save(existing);
    }

    // =========================
    // DELETE ZONE
    // =========================
    @Transactional
    public void deleteZone(Long id) {
        repository.delete(getZoneById(id));
    }
}