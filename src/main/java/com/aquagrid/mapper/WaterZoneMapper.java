package com.aquagrid.mapper;

import com.aquagrid.dto.WaterZoneRequest;
import com.aquagrid.dto.WaterZoneResponse;
import com.aquagrid.model.WaterZone;
import org.springframework.stereotype.Component;

@Component
public class WaterZoneMapper {

    // =========================
    // REQUEST DTO -> ENTITY
    // =========================
    public WaterZone toEntity(WaterZoneRequest request) {

        WaterZone zone = new WaterZone();

        zone.setZoneName(request.getZoneName());
        zone.setCity(request.getCity());
        zone.setWaterLevel(request.getWaterLevel());

        return zone;
    }

    // =========================
    // ENTITY -> RESPONSE DTO
    // =========================
    public WaterZoneResponse toResponse(WaterZone zone) {

        return WaterZoneResponse.builder()
                .id(zone.getId())
                .zoneName(zone.getZoneName())
                .city(zone.getCity())
                .waterLevel(zone.getWaterLevel())
                .status(zone.getStatus())

                // ✅ NEW
                .createdAt(zone.getCreatedAt())
                .updatedAt(zone.getUpdatedAt())

                .build();
    }
}