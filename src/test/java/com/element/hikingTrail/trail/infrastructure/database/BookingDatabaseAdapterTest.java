package com.element.hikingTrail.trail.infrastructure.database;

import com.element.hikingTrail.trail.application.exception.BookingNotFound;
import com.element.hikingTrail.trail.domain.Booking;
import com.element.hikingTrail.trail.domain.Trail;
import com.element.hikingTrail.trail.infrastructure.BookingMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static java.lang.Integer.parseInt;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookingDatabaseAdapterTest {


    @Mock
    private BookingRepository bookingRepository;
    @Spy
    private BookingMapper mapper = Mappers.getMapper(BookingMapper.class);
    @InjectMocks
    private BookingDatabaseAdapter bookingDatabaseAdapter;

    @Test
    public void shouldSaveBookingInTheDBAndReturnBookingDomain() {
        Trail trail = Trail.builder()
                .name("trailName")
                .endAt("12:00")
                .maximumAge(50)
                .minimumAge(10)
                .startAt("07:00")
                .unitPrice(150)
                .build();
        var booking = Booking.builder()
                .trail(trail)
                .bookingId("id")
                .bookingStatus("status")
                .build();
        var bookingEntity = BookingEntity.builder()
                .bookingId("id")
                .trail(trail)
                .bookingStatus("status")
                .build();
        when(bookingRepository.save(any(BookingEntity.class))).thenReturn(bookingEntity);

        Booking actual = bookingDatabaseAdapter.saveBooking(booking);

        verify(bookingRepository).save(bookingEntity);
        assertThat(actual).isEqualTo(booking);
    }

    @Test
    public void shouldFindBookEntityByIdAndReturnBooking() {
        Trail trail = Trail.builder()
                .name("trailName")
                .endAt("12:00")
                .maximumAge(50)
                .minimumAge(10)
                .startAt("07:00")
                .unitPrice(150)
                .build();
        var bookingId = "fdsdf";
        var booking = Booking.builder()
                .trail(trail)
                .bookingId(bookingId)
                .bookingStatus("status")
                .build();
        var bookingEntity = BookingEntity.builder()
                .bookingId(bookingId)
                .trail(trail)
                .bookingStatus("status")
                .build();
        when(bookingRepository.findByBookingId(bookingId)).thenReturn(Optional.of(bookingEntity));

        Booking actual = bookingDatabaseAdapter.findByBookingId(bookingId);

        verify(bookingRepository).findByBookingId(bookingId);
        assertThat(actual).isEqualTo(booking);
    }

    @Test
    public void shouldThrowBookingNotFoundWhenDatabaseFindByIdComesEmpty() {
        when(bookingRepository.findByBookingId("abc")).thenReturn(Optional.empty());
        Exception exception = assertThrows(BookingNotFound.class,
                () -> bookingDatabaseAdapter.findByBookingId("abc"));
        String expectedMessage = "Booking with id: abc not found";

        String actualMessage = exception.getMessage();

        assertThat(actualMessage).isEqualTo(expectedMessage);
    }
}