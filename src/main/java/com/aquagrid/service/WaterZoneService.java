package com.aquagrid.service;

import com.aquagrid.exception.ResourceNotFoundException;
import com.aquagrid.exception.ZoneAlreadyExistsException;
import com.aquagrid.model.WaterZone;
import com.aquagrid.model.ZoneStatus;
import com.aquagrid.repository.WaterZoneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WaterZoneService {

    private final WaterZoneRepository repository;

    // =========================
    // STATUS CALCULATION RULES
    // =========================
    private ZoneStatus calculateStatus(Double waterLevel) {

        if (waterLevel == null || waterLevel <= 40) return ZoneStatus.CRITICAL;
        if (waterLevel <= 70) return ZoneStatus.WARNING;
        return ZoneStatus.ACTIVE;
    }

    // =========================
    // CREATE ZONE
    // =========================
    @Transactional
    public WaterZone createZone(WaterZone zone) {

        if (repository.existsByZoneName(zone.getZoneName())) {
            throw new ZoneAlreadyExistsException(
                    "Zone already exists: " + zone.getZoneName()
            );
        }

        zone.setStatus(calculateStatus(zone.getWaterLevel()));

        return repository.save(zone);
    }

    // =========================
    // GET ALL ZONES
    // =========================
    public List<WaterZone> getAllZones() {
        return repository.findAll();
    }

    public List<WaterZone> searchByCity(String city) {

        if (city == null || city.trim().isEmpty()) {
            throw new IllegalArgumentException("City cannot be empty");
        }

        return repository.findByCity(city.trim());
    }

    // =========================
    // GET BY ID
    // =========================
    public WaterZone getZoneById(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Zone not found: " + id)
                );
    }

    // =========================
    // UPDATE ZONE (FIXED VERSION)
    // =========================
    @Transactional
    public WaterZone updateZone(Long id, WaterZone updated) {

        WaterZone existing = getZoneById(id);

        // ⚠️ FIX: prevent duplicate zone name on update
        if (!existing.getZoneName().equals(updated.getZoneName())
                && repository.existsByZoneName(updated.getZoneName())) {
            throw new ZoneAlreadyExistsException(
                    "Zone already exists: " + updated.getZoneName()
            );
        }

        existing.setZoneName(updated.getZoneName());
        existing.setCity(updated.getCity());
        existing.setWaterLevel(updated.getWaterLevel());

        // always recalculate status
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

    // =========================
    // NEW FEATURE: SEARCH BY CITY
    // =========================
    public List<WaterZone> getZonesByCity(String city) {
        return repository.findByCity(city);
    }
}