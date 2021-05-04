package com.element.hikingTrail.trail.infrastructure.rest;

import com.element.hikingTrail.IntegrationTest;
import com.element.hikingTrail.trail.domain.*;
import com.element.hikingTrail.trail.infrastructure.database.BookingEntity;
import com.element.hikingTrail.trail.infrastructure.database.BookingRepository;
import com.element.hikingTrail.trail.infrastructure.database.TrailEntity;
import com.element.hikingTrail.trail.infrastructure.database.TrailRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
class BookingControllerTest extends IntegrationTest {

    private final BookingController bookingController;
    private final BookingRepository bookingRepository;
    private final TrailRepository trailRepository;

    @Test
    @DisplayName("POST /booking")
    public void whenPostBooking_withRequestBody_shouldReturnSavedBooking_WithStatusAndId() {
        trailRepository.save(TrailEntity.builder()
                .name("trail")
                .endAt("12:00")
                .maximumAge(50)
                .minimumAge(10)
                .startAt("07:00")
                .unitPrice(150)
                .build());
        var hikers = singletonList(Hiker.builder()
                .name("Raul")
                .age(27)
                .build());
        var expectedTrail = Trail.builder()
                .name("trail")
                .endAt("12:00")
                .maximumAge(50)
                .minimumAge(10)
                .startAt("07:00")
                .unitPrice(150)
                .build();
        var bookingRequest = BookingRequest.builder()
                .trailName("trail")
                .bookingDetails(BookingDetail.builder()
                        .hikers(hikers)
                        .build())
                .build();

        ResponseEntity<BookingResponse> response = bookingController.bookTrail(bookingRequest);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getBooking().getTrail())
                .isEqualTo(expectedTrail);
        assertThat(response.getBody().getBooking().getBookingDetails().getHikers())
                .isEqualTo(bookingRequest.getBookingDetails().getHikers());
        assertThat(response.getBody().getBooking().getBookingId())
                .isNotEmpty();
        assertThat(response.getBody().getBooking().getBookingStatus())
                .isEqualTo(BookingStatus.BOOKED.name());
    }

    @Test
    @DisplayName("GET /booking?bookingId=x")
    public void viewBooking() {
        String bookingId = "vxzcf";
        Trail trail = Trail.builder()
                .name("trailName")
                .endAt("12:00")
                .maximumAge(50)
                .minimumAge(10)
                .startAt("07:00")
                .unitPrice(150)
                .build();
        trailRepository.save(TrailEntity.builder()
                .name(trail.getName())
                .endAt(trail.getEndAt())
                .maximumAge(trail.getMaximumAge())
                .minimumAge(trail.getMinimumAge())
                .startAt(trail.getStartAt())
                .unitPrice(trail.getUnitPrice())
                .build());
        bookingRepository.save(BookingEntity.builder()
                .bookingId(bookingId)
                .trail(trail)
                .bookingStatus(BookingStatus.BOOKED.name())
                .build());
        var expectedResponse = BookingResponse.builder()
                .booking(Booking.builder()
                        .bookingId(bookingId)
                        .trail(trail)
                        .bookingStatus(BookingStatus.BOOKED.name())
                        .build())
                .build();

        ResponseEntity<BookingResponse> response = bookingController.getBooking(bookingId);

        assertThat(response.getBody()).isEqualTo(expectedResponse);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    @DisplayName("DELETE /booking?bookingId=fdsdf")
    public void whenDeleteBooking_withBookingIdAsParam_shouldChangeStatusToCanceled() {
        String bookingId = "dsasd";
        Trail trail = Trail.builder()
                .name("trailName")
                .endAt("12:00")
                .maximumAge(50)
                .minimumAge(10)
                .startAt("07:00")
                .unitPrice(150)
                .build();
        trailRepository.save(TrailEntity.builder()
                .name(trail.getName())
                .endAt(trail.getEndAt())
                .maximumAge(trail.getMaximumAge())
                .minimumAge(trail.getMinimumAge())
                .startAt(trail.getStartAt())
                .unitPrice(trail.getUnitPrice())
                .build());
        bookingRepository.save(BookingEntity.builder()
                .bookingId(bookingId)
                .trail(trail)
                .bookingStatus(BookingStatus.BOOKED.name())
                .build());
        var booking = Booking.builder()
                .bookingStatus(BookingStatus.CANCELED.name())
                .trail(trail)
                .bookingId(bookingId)
                .build();
        var expectedResponse = BookingResponse.builder()
                .booking(booking)
                .build();

        ResponseEntity<BookingResponse> response = bookingController.cancelBooking(bookingId);

        assertThat(response.getBody()).isEqualTo(expectedResponse);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}