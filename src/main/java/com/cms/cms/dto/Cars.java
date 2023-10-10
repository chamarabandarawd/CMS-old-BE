package com.cms.cms.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Cars {

    private int carId;
    private String carNumber;
    private int passengerCount;
    private String gearSystem;
    private int fuelEfficiency;
}
