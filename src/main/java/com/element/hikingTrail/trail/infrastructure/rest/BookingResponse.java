package com.element.hikingTrail.trail.infrastructure.rest;

import com.element.hikingTrail.trail.domain.Booking;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
public class BookingResponse {
    private final Booking booking;
}
