package com.aquagrid.controller;

import com.aquagrid.dto.WaterZoneRequest;
import com.aquagrid.dto.WaterZoneResponse;
import com.aquagrid.mapper.WaterZoneMapper;
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

    private final WaterZoneService waterZoneService;

    @PostMapping
    public ResponseEntity<WaterZoneResponse> create(@Valid @RequestBody WaterZoneRequest request) {

        var entity = WaterZoneMapper.toEntity(request);
        var saved = waterZoneService.createZone(entity);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(WaterZoneMapper.toResponse(saved));
    }

    @GetMapping
    public ResponseEntity<List<WaterZoneResponse>> getAll() {

        var zones = waterZoneService.getAllZones();

        return ResponseEntity.ok(
                zones.stream().map(WaterZoneMapper::toResponse).toList()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<WaterZoneResponse> getById(@PathVariable Long id) {

        return ResponseEntity.ok(
                WaterZoneMapper.toResponse(waterZoneService.getZoneById(id))
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<WaterZoneResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody WaterZoneRequest request) {

        var updatedEntity = WaterZoneMapper.toEntity(request);
        var updated = waterZoneService.updateZone(id, updatedEntity);

        return ResponseEntity.ok(
                WaterZoneMapper.toResponse(updated)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        waterZoneService.deleteZone(id);
        return ResponseEntity.noContent().build();
    }
}