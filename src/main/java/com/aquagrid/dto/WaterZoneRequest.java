package com.aquagrid.dto;

import lombok.Data;

@Data
public class WaterZoneRequest {
    private String zoneName;
    private String city;
    private Double waterLevel;
}