package com.element.hikingTrail.trail.infrastructure.rest;

import com.element.hikingTrail.IntegrationTest;
import com.element.hikingTrail.trail.domain.Booking;
import com.element.hikingTrail.trail.domain.BookingDetail;
import com.element.hikingTrail.trail.domain.BookingStatus;
import com.element.hikingTrail.trail.domain.Hiker;
import com.element.hikingTrail.trail.infrastructure.database.BookingEntity;
import com.element.hikingTrail.trail.infrastructure.database.BookingRepository;
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

    @Test
    @DisplayName("POST /booking")
    public void whenPostBooking_withRequestBody_shouldSaveBookingInTheDatabase() {
        var bookingRequest = BookingRequest.builder()
                .trailName("trail")
                .bookingDetail(BookingDetail.builder()
                        .hikers(singletonList(Hiker.builder()
                                .name("Raul")
                                .age(27)
                                .build()))
                        .build())
                .build();

        ResponseEntity<BookingResponse> response = bookingController.bookTrail(bookingRequest);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getBooking().getTrailName())
                .isEqualTo(bookingRequest.getTrailName());
        assertThat(response.getBody().getBooking().getBookingStatus())
                .isEqualTo(BookingStatus.BOOKED.name());
    }

    @Test
    @DisplayName("GET /booking?bookingId=x")
    public void viewBooking() {
        String bookingId = "vxzcf";
        bookingRepository.save(BookingEntity.builder()
                .bookingId(bookingId)
                .trailName("Shire")
                .bookingStatus(BookingStatus.BOOKED.name())
                .build());
        var expectedResponse = BookingResponse.builder()
                .booking(Booking.builder()
                        .bookingId(bookingId)
                        .trailName("Shire")
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
        bookingRepository.save(BookingEntity.builder()
                .bookingId(bookingId)
                .trailName("Shire")
                .bookingStatus(BookingStatus.BOOKED.name())
                .build());
        var booking = Booking.builder()
                .bookingStatus(BookingStatus.CANCELED.name())
                .trailName("Shire")
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