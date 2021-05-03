package com.element.hikingTrail.trail.infrastructure.database;

import com.element.hikingTrail.trail.application.BookingPersistencePort;
import com.element.hikingTrail.trail.application.exception.BookingNotFound;
import com.element.hikingTrail.trail.domain.Booking;
import com.element.hikingTrail.trail.infrastructure.BookingMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class BookingDatabaseAdapter implements BookingPersistencePort {

    private final BookingRepository bookingRepository;
    private final BookingMapper bookingMapper;

    @Override
    public Booking saveBooking(Booking booking) {

        BookingEntity bookingEntity = bookingMapper.mapFromDomainToEntity(booking);

        BookingEntity saved = bookingRepository.save(bookingEntity);

        return bookingMapper.mapFromEntityToDomain(saved);
    }

    @Override
    @SneakyThrows
    public Booking findByBookingId(String bookingId) {
        BookingEntity bookingEntity =
                bookingRepository.findByBookingId(bookingId).orElseThrow(() ->
                        new BookingNotFound("Booking with id: "+ bookingId +" not found"));

        return bookingMapper.mapFromEntityToDomain(bookingEntity);
    }
}
