package com.element.hikingTrail.trail.application;

import com.element.hikingTrail.trail.application.exception.HikerNotEligible;
import com.element.hikingTrail.trail.domain.Booking;
import com.element.hikingTrail.trail.domain.BookingStatus;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingPersistencePort bookingDatabaseAdapter;

    @SneakyThrows
    public Booking bookTrail(Booking booking) {
        if (!booking.areHikersWithinAgeRange()) {
            throw new HikerNotEligible("One or more hikers is above or below age limit");
        }

        var bookedTrail = Booking.builder()
                .trail(booking.getTrail())
                .bookingDetails(booking.getBookingDetails())
                .bookingId(generateId())
                .bookingStatus(BookingStatus.BOOKED.name())
                .build();

        log.trace("[BookingService@bookTrail] - Trail booked: {}", bookedTrail);
        return bookingDatabaseAdapter.saveBooking(bookedTrail);
    }

    public Booking findBooking(String bookingId) {
        return bookingDatabaseAdapter.findByBookingId(bookingId);
    }

    public Booking cancelBooking(String bookingId) {

        Booking booking = bookingDatabaseAdapter.findByBookingId(bookingId);

        booking.setBookingStatus(BookingStatus.CANCELED.name());

        return bookingDatabaseAdapter.saveBooking(booking);
    }

    private String generateId() {
        return RandomStringUtils.randomAlphanumeric(5);
    }
}
