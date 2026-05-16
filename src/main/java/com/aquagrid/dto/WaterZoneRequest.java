package com.aquagrid.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class WaterZoneRequest {

    @NotBlank(message = "Zone name is required")
    private String zoneName;

    @NotBlank(message = "City is required")
    private String city;

    @NotNull(message = "Water level is required")
    private Double waterLevel;
}