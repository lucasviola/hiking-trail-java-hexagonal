package com.element.hikingTrail.trail.infrastructure.rest;

import com.element.hikingTrail.trail.domain.BookingDetail;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookingRequest {

    private String trailName;
    private BookingDetail bookingDetail;
}
