package com.aquagrid.controller;

import com.aquagrid.dto.WaterZoneRequest;
import com.aquagrid.mapper.WaterZoneMapper;
import com.aquagrid.response.ApiResponse;
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
    public ResponseEntity<ApiResponse<Object>> create(@Valid @RequestBody WaterZoneRequest request) {

        var entity = WaterZoneMapper.toEntity(request);
        var saved = waterZoneService.createZone(entity);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(
                        true,
                        "Water zone created successfully",
                        WaterZoneMapper.toResponse(saved)
                ));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Object>>> getAll() {

        var zones = waterZoneService.getAllZones()
                .stream()
                .map(WaterZoneMapper::toResponse)
                .toList();

        return ResponseEntity.ok(
                new ApiResponse<>(true, "All zones fetched successfully", (List) zones)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Object>> getById(@PathVariable Long id) {

        var zone = waterZoneService.getZoneById(id);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Zone fetched successfully",
                        WaterZoneMapper.toResponse(zone)
                )
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Object>> update(
            @PathVariable Long id,
            @Valid @RequestBody WaterZoneRequest request) {

        var updated = waterZoneService.updateZone(id, WaterZoneMapper.toEntity(request));

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Zone updated successfully",
                        WaterZoneMapper.toResponse(updated)
                )
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Object>> delete(@PathVariable Long id) {

        waterZoneService.deleteZone(id);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Zone deleted successfully",
                        null
                )
        );
    }
}