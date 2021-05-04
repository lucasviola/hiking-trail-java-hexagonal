package com.element.hikingTrail.trail.infrastructure.rest;

import com.element.hikingTrail.trail.domain.BookingDetail;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookingRequest {

    private String trailName;
    private BookingDetail bookingDetails;
}
