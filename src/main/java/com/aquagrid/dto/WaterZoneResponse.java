package com.aquagrid.dto;

import com.aquagrid.model.ZoneStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WaterZoneResponse {

    private Long id;
    private String zoneName;
    private String city;
    private Double waterLevel;
    private ZoneStatus status;
}