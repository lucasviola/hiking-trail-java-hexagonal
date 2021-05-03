package com.element.hikingTrail.trail.application;

import com.element.hikingTrail.trail.domain.Booking;
import com.element.hikingTrail.trail.domain.BookingStatus;
import com.element.hikingTrail.trail.domain.Hiker;
import com.element.hikingTrail.trail.domain.Trail;
import com.element.hikingTrail.trail.infrastructure.database.BookingDatabaseAdapter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static java.util.Collections.singletonList;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookingServiceTest {

    @Mock
    private BookingDatabaseAdapter bookingDatabaseAdapter;

    @InjectMocks
    private BookingService bookingService;

    @Test
    public void whenBookingGetsSaved_statusShouldBeBooked_AndBookingIdShouldNotBeNull() {
        Trail trail = Trail.builder()
                .name("trailName")
                .endAt("12:00")
                .maximumAge(50)
                .minimumAge(10)
                .startAt("07:00")
                .unitPrice(150)
                .build();
        Booking booking = Booking.builder()
                .trail(trail)
                .build();
        Booking bookedTrail = Booking.builder()
                .trail(trail)
                .bookingId("id")
                .bookingStatus(BookingStatus.BOOKED.name())
                .build();

        bookingService.bookTrail(booking);

//        verify(bookingDatabaseAdapter).saveBooking(eq(bookedTrail));
    }

    @Test
    public void shouldFindBookByBookId() {
        Trail trail = Trail.builder()
                .name("trailName")
                .endAt("12:00")
                .maximumAge(50)
                .minimumAge(10)
                .startAt("07:00")
                .unitPrice(150)
                .build();
        String bookingId = "xvcfg";
        Booking bookedTrail = Booking.builder()
                .trail(trail)
                .bookingId(bookingId)
                .bookingStatus(BookingStatus.BOOKED.name())
                .build();
        when(bookingDatabaseAdapter.findByBookingId(bookingId)).thenReturn(bookedTrail);

        bookingService.findBooking(bookingId);

        verify(bookingDatabaseAdapter).findByBookingId(bookingId);
    }

    @Test
    public void whenCancel_shouldUpdateBookingWithCanceledStatus() {
        var trail = Trail.builder()
                .name("trailName")
                .endAt("12:00")
                .maximumAge(50)
                .minimumAge(10)
                .startAt("07:00")
                .unitPrice(150)
                .build();
        var hikers = singletonList(Hiker.builder()
                .name("Raul")
                .age(27)
                .build());
        var bookingId = "xvcfg";
        Booking bookedTrail = Booking.builder()
                .trail(trail)
                .hikers(hikers)
                .bookingId(bookingId)
                .bookingStatus(BookingStatus.BOOKED.name())
                .build();
        Booking expectedCanceledBooking = Booking.builder()
                .trail(trail)
                .hikers(hikers)
                .bookingId(bookingId)
                .bookingStatus(BookingStatus.CANCELED.name())
                .build();
        when(bookingDatabaseAdapter.findByBookingId(bookingId)).thenReturn(bookedTrail);

        bookingService.cancelBooking(bookingId);

        verify(bookingDatabaseAdapter).saveBooking(expectedCanceledBooking);
    }
}