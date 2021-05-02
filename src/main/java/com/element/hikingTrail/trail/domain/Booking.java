package com.element.hikingTrail.trail.domain;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Booking {

    private String bookingId;
    private String trailName;
    private String bookingStatus;
}
