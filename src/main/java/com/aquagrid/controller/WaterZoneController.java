package com.aquagrid.controller;

import com.aquagrid.dto.WaterZoneRequest;
import com.aquagrid.dto.WaterZoneResponse;
import com.aquagrid.mapper.WaterZoneMapper;
import com.aquagrid.response.ApiResponse;
import com.aquagrid.service.WaterZoneService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/zones")
@RequiredArgsConstructor
public class WaterZoneController {

    private final WaterZoneService service;

    @PostMapping
    public ResponseEntity<ApiResponse<WaterZoneResponse>> create(
            @Valid @RequestBody WaterZoneRequest request) {

        var saved = service.createZone(
                WaterZoneMapper.toEntity(request)
        );

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Zone created successfully",
                        WaterZoneMapper.toResponse(saved)
                )
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<WaterZoneResponse>>> getAll() {

        var list = service.getAllZones()
                .stream()
                .map(WaterZoneMapper::toResponse)
                .toList();

        return ResponseEntity.ok(
                ApiResponse.success("All zones", list)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<WaterZoneResponse>> getById(@PathVariable Long id) {

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Zone found",
                        WaterZoneMapper.toResponse(service.getZoneById(id))
                )
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<WaterZoneResponse>> update(
            @PathVariable Long id,
            @Valid @RequestBody WaterZoneRequest request) {

        var updated = service.updateZone(
                id,
                WaterZoneMapper.toEntity(request)
        );

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Zone updated",
                        WaterZoneMapper.toResponse(updated)
                )
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {

        service.deleteZone(id);

        return ResponseEntity.ok(
                ApiResponse.success("Zone deleted", null)
        );
    }
}