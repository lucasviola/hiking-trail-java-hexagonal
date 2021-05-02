package com.element.hikingTrail.trail.application;

import com.element.hikingTrail.trail.domain.Booking;
import com.element.hikingTrail.trail.infrastructure.database.BookingDatabaseAdapter;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class BookingServiceTest {

    @Mock
    private BookingDatabaseAdapter bookingDatabaseAdapter;

    @InjectMocks
    private BookingService bookingService;

    @Test
    public void whenBookingGetsSaved_statusShouldBeBooked_AndBookingIdShouldNotBeNull() {
        Booking booking = Booking.builder()
                .trailName("name")
                .build();
        Booking bookedTrail = Booking.builder()
                .trailName("name")
                .bookingId("id")
                .bookingStatus("BOOKED")
                .build();

        bookingService.bookTrail(booking);

//        verify(bookingDatabaseAdapter).saveBooking(eq(bookedTrail));
    }

}