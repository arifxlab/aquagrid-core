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

    private ZoneStatus calculateStatus(Double waterLevel) {

        if (waterLevel == null || waterLevel <= 40) return ZoneStatus.CRITICAL;
        if (waterLevel <= 70) return ZoneStatus.WARNING;
        return ZoneStatus.ACTIVE;
    }

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

    public List<WaterZone> getAllZones() {
        return repository.findAll();
    }

    public WaterZone getZoneById(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Zone not found: " + id)
                );
    }

    @Transactional
    public WaterZone updateZone(Long id, WaterZone updated) {

        WaterZone existing = getZoneById(id);

        existing.setZoneName(updated.getZoneName());
        existing.setCity(updated.getCity());
        existing.setWaterLevel(updated.getWaterLevel());
        existing.setStatus(calculateStatus(updated.getWaterLevel()));

        return repository.save(existing);
    }

    @Transactional
    public void deleteZone(Long id) {
        repository.delete(getZoneById(id));
    }
}