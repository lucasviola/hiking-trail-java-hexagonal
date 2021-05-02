package com.element.hikingTrail.trail.application;

import com.element.hikingTrail.trail.domain.Booking;
import com.element.hikingTrail.trail.domain.BookingStatus;
import com.element.hikingTrail.trail.infrastructure.database.BookingDatabaseAdapter;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingPersistencePort bookingDatabaseAdapter;

    public Booking bookTrail(Booking booking) {

        var bookedTrail = Booking.builder()
                .trailName(booking.getTrailName())
                .bookingId(generateId())
                .bookingStatus(BookingStatus.BOOKED.name())
                .build();

        return bookingDatabaseAdapter.saveBooking(bookedTrail);
    }

    private String generateId() {
        return RandomStringUtils.randomAlphanumeric(5);
    }
}
