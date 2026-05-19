package com.aquagrid.controller;

import com.aquagrid.dto.WaterZoneRequest;
import com.aquagrid.dto.WaterZoneResponse;
import com.aquagrid.mapper.WaterZoneMapper;
import com.aquagrid.model.WaterZone;
import com.aquagrid.model.ZoneStatus;
import com.aquagrid.response.ApiResponse;
import com.aquagrid.service.WaterZoneService;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
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

        WaterZoneResponse response =
                mapper.toResponse(savedZone);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(
                        ApiResponse.success(
                                "Zone created successfully",
                                response
                        )
                );
    }

    // =========================
    // GET ALL ZONES WITH PAGINATION
    // =========================
    @GetMapping
    public ResponseEntity<ApiResponse<Page<WaterZoneResponse>>> getAllZones(

            @RequestParam(defaultValue = "0")
            int page,

            @RequestParam(defaultValue = "5")
            int size,

            @RequestParam(defaultValue = "id")
            String sortBy
    ) {

        Page<WaterZoneResponse> response =
                service.getAllZones(page, size, sortBy)
                        .map(mapper::toResponse);

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Zones fetched successfully",
                        response
                )
        );
    }

    // =========================
    // SEARCH BY CITY
    // =========================
    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<WaterZoneResponse>>> searchByCity(
            @RequestParam String city
    ) {

        List<WaterZoneResponse> response =
                service.searchByCity(city)
                        .stream()
                        .map(mapper::toResponse)
                        .toList();

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Zones found",
                        response
                )
        );
    }

    // =========================
    // FILTER BY STATUS
    // =========================
    @GetMapping("/status/{status}")
    public ResponseEntity<ApiResponse<List<WaterZoneResponse>>> getZonesByStatus(
            @PathVariable ZoneStatus status
    ) {

        List<WaterZoneResponse> response =
                service.getZonesByStatus(status)
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

        WaterZone updatedZone =
                service.updateZone(
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