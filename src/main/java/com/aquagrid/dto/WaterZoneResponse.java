package com.aquagrid.dto;

import com.aquagrid.model.ZoneStatus;
import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Builder
public class WaterZoneResponse {

    private Long id;
    private String zoneName;
    private String city;
    private Double waterLevel;
    private ZoneStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}