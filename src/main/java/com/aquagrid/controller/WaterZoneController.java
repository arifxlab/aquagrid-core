package com.aquagrid.controller;

import com.aquagrid.model.WaterZone;
import com.aquagrid.service.WaterZoneService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/zones")
@RequiredArgsConstructor
public class WaterZoneController {

    private final WaterZoneService service;

    @PostMapping
    public ResponseEntity<WaterZone> create(@Valid @RequestBody WaterZone zone) {
        return new ResponseEntity<>(service.createZone(zone), HttpStatus.CREATED);
    }

    @GetMapping
    public List<WaterZone> getAll() {
        return service.getAllZones();
    }

    @GetMapping("/{id}")
    public WaterZone getById(@PathVariable Long id) {
        return service.getZoneById(id);
    }

    @PutMapping("/{id}")
    public WaterZone update(@PathVariable Long id,
                            @Valid @RequestBody WaterZone zone) {
        return service.updateZone(id, zone);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteZone(id);
        return ResponseEntity.noContent().build();
    }
}