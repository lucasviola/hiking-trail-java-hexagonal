package com.element.hikingTrail.trail;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Trail {

    private String name;
    private String startAt;
    private String endAt;
    private int minimumAge;
    private int maximumAge;
    private double unitPrice;
}
