package com.element.hikingTrail.trail.infrastructure.rest;

import com.element.hikingTrail.trail.domain.Booking;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookingResponse {
    private Booking booking;
}
