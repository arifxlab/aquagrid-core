package com.aquagrid.service;

import com.aquagrid.model.WaterZone;
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

    @Transactional
    public WaterZone createZone(WaterZone zone) {

        if (repository.existsByZoneName(zone.getZoneName())) {
            throw new RuntimeException("Zone already exists: " + zone.getZoneName());
        }

        return repository.save(zone);
    }

    @Transactional(readOnly = true)
    public List<WaterZone> getAllZones() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public WaterZone getZoneById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Zone not found: " + id));
    }

    @Transactional
    public WaterZone updateZone(Long id, WaterZone updated) {

        WaterZone existing = getZoneById(id);

        existing.setZoneName(updated.getZoneName());
        existing.setCity(updated.getCity());
        existing.setWaterLevel(updated.getWaterLevel());

        return repository.save(existing);
    }

    @Transactional
    public void deleteZone(Long id) {
        repository.delete(getZoneById(id));
    }
}