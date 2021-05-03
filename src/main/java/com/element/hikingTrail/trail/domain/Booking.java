package com.element.hikingTrail.trail.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Booking {

    private String bookingId;
    private Trail trail;
    private BookingDetail bookingDetail;
    private String bookingStatus;

}
