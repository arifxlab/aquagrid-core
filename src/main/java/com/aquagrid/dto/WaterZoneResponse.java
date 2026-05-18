package com.aquagrid.dto;

import com.aquagrid.model.ZoneStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WaterZoneResponse {

    private Long id;
    private String zoneName;
    private String city;
    private Double waterLevel;
    private ZoneStatus status;
}