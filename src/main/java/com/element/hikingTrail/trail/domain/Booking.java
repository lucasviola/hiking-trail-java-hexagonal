package com.element.hikingTrail.trail.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Booking {

    private String bookingId;
    private Trail trail;
    private BookingDetail bookingDetails;
    private String bookingStatus;


    public boolean areHikersWithinAgeRange() {
        List<Hiker> notEligibleHikers = bookingDetails.getHikers().stream()
                .filter(hiker -> hiker.getAge() < trail.getMinimumAge()
                        || hiker.getAge() > trail.getMaximumAge())
                .collect(toList());

        return notEligibleHikers.size() == 0;
    }
}
