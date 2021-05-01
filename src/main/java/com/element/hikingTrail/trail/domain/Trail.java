package com.element.hikingTrail.trail.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
