package com.aquagrid.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class WaterZoneRequest {

    @NotBlank
    private String zoneName;

    @NotBlank
    private String city;

    @NotNull
    @PositiveOrZero
    private Double waterLevel;
}