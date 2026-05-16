package com.aquagrid.controller;

import com.aquagrid.dto.WaterZoneRequest;
import com.aquagrid.dto.WaterZoneResponse;
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
    public ResponseEntity<ApiResponse<WaterZoneResponse>> create(
            @Valid @RequestBody WaterZoneRequest request) {

        var entity = WaterZoneMapper.toEntity(request);
        var saved = waterZoneService.createZone(entity);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(
                        "Water zone created successfully",
                        WaterZoneMapper.toResponse(saved)
                ));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<WaterZoneResponse>>> getAll() {

        var zones = waterZoneService.getAllZones()
                .stream()
                .map(WaterZoneMapper::toResponse)
                .toList();

        return ResponseEntity.ok(
                ApiResponse.success(
                        "All zones fetched successfully",
                        zones
                )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<WaterZoneResponse>> getById(@PathVariable Long id) {

        var zone = waterZoneService.getZoneById(id);

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Zone fetched successfully",
                        WaterZoneMapper.toResponse(zone)
                )
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<WaterZoneResponse>> update(
            @PathVariable Long id,
            @Valid @RequestBody WaterZoneRequest request) {

        var updated = waterZoneService.updateZone(id, WaterZoneMapper.toEntity(request));

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Zone updated successfully",
                        WaterZoneMapper.toResponse(updated)
                )
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {

        waterZoneService.deleteZone(id);

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Zone deleted successfully",
                        null
                )
        );
    }
}