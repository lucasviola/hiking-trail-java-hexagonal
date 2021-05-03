package com.element.hikingTrail.trail.application;

import com.element.hikingTrail.trail.domain.Booking;
import com.element.hikingTrail.trail.domain.BookingStatus;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingPersistencePort bookingDatabaseAdapter;

    @SneakyThrows
    public Booking bookTrail(Booking booking) {
//        booking.getHikers()
//                .forEach(hiker -> {
//                    if (hiker.getAge() < booking.getTrailName().getMinimumAge() || hiker.getAge() > booking.getTrailName().getMaximumAge()) {
//                        throw new AgeNotInRange("Age Not in range");
//                    }
//                });

        var bookedTrail = Booking.builder()
                .trail(booking.getTrail())
                .hikers(booking.getHikers())
                .bookingId(generateId())
                .bookingStatus(BookingStatus.BOOKED.name())
                .build();

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
