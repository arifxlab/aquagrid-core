package com.aquagrid.mapper;

import com.aquagrid.dto.WaterZoneRequest;
import com.aquagrid.dto.WaterZoneResponse;
import com.aquagrid.model.WaterZone;

public class WaterZoneMapper {

    public static WaterZone toEntity(WaterZoneRequest request) {
        return WaterZone.builder()
                .zoneName(request.getZoneName())
                .city(request.getCity())
                .waterLevel(request.getWaterLevel())
                .build();
    }

    public static WaterZoneResponse toResponse(WaterZone zone) {
        return WaterZoneResponse.builder()
                .id(zone.getId())
                .zoneName(zone.getZoneName())
                .city(zone.getCity())
                .waterLevel(zone.getWaterLevel())
                .status(zone.getStatus())
                .build();
    }
}