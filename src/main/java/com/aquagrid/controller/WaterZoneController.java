package com.aquagrid.controller;

import com.aquagrid.dto.WaterZoneRequest;
import com.aquagrid.dto.WaterZoneResponse;
import com.aquagrid.mapper.WaterZoneMapper;
import com.aquagrid.model.WaterZone;
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

    private final WaterZoneService service;
    private final WaterZoneMapper mapper;

    // =========================
    // CREATE ZONE
    // =========================
    @PostMapping
    public ResponseEntity<ApiResponse<WaterZoneResponse>> createZone(
            @Valid @RequestBody WaterZoneRequest request
    ) {

        WaterZone savedZone = service.createZone(
                mapper.toEntity(request)
        );

        WaterZoneResponse response = mapper.toResponse(savedZone);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(
                        "Zone created successfully",
                        response
                ));
    }

    // =========================
    // GET ALL ZONES
    // =========================
    @GetMapping
    public ResponseEntity<ApiResponse<List<WaterZoneResponse>>> getAllZones() {

        List<WaterZoneResponse> response = service.getAllZones()
                .stream()
                .map(mapper::toResponse)
                .toList();

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Zones fetched successfully",
                        response
                )
        );
    }

    // =========================
    // GET ZONE BY ID
    // =========================
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<WaterZoneResponse>> getZoneById(
            @PathVariable Long id
    ) {

        WaterZone zone = service.getZoneById(id);

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Zone fetched successfully",
                        mapper.toResponse(zone)
                )
        );
    }

    // =========================
    // UPDATE ZONE
    // =========================
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<WaterZoneResponse>> updateZone(
            @PathVariable Long id,
            @Valid @RequestBody WaterZoneRequest request
    ) {

        WaterZone updatedZone = service.updateZone(
                id,
                mapper.toEntity(request)
        );

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Zone updated successfully",
                        mapper.toResponse(updatedZone)
                )
        );
    }

    // =========================
    // DELETE ZONE
    // =========================
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteZone(
            @PathVariable Long id
    ) {

        service.deleteZone(id);

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Zone deleted successfully",
                        null
                )
        );
    }
}