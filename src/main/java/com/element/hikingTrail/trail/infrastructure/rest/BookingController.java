package com.element.hikingTrail.trail.infrastructure.rest;

import com.element.hikingTrail.trail.application.BookingService;
import com.element.hikingTrail.trail.application.TrailService;
import com.element.hikingTrail.trail.domain.Booking;
import com.element.hikingTrail.trail.domain.Trail;
import com.element.hikingTrail.trail.infrastructure.BookingMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class BookingController {
    private final BookingService bookingService;
    private final TrailService trailService;
    private final BookingMapper bookingMapper;

    @PostMapping("/booking")
    public ResponseEntity<BookingResponse> bookTrail(@RequestBody BookingRequest bookingRequest) {
        log.trace("[BookingController@bookTrail] - Booking request received: {}", bookingRequest);

        Trail trail = trailService.findByName(bookingRequest.getTrailName());

        Booking booking = bookingMapper.mapFromRequestToDomain(bookingRequest, trail);
        Booking bookedTrail = bookingService.bookTrail(booking);

        var responseBody =
                bookingMapper.mapFromBookingToBookingResponse(bookedTrail);

        log.trace("[BookingController@bookTrail] - Trail booked succesfully!: {}", responseBody);
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

    @GetMapping("/booking/{bookingId}")
    public ResponseEntity<BookingResponse> getBooking(@PathVariable @NonNull String bookingId) {
        log.trace("[BookingController@getBooking] - Get Booking request received for id: {}", bookingId);
        var bookedTrail = bookingService.findBooking(bookingId);

        var responseBody =
                bookingMapper.mapFromBookingToBookingResponse(bookedTrail);

        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

    @DeleteMapping("/booking/{bookingId}")
    public ResponseEntity<BookingResponse> cancelBooking(@PathVariable @NonNull String bookingId) {
        log.trace("[BookingController@cancelBooking] - Request received for canceling booking id: {}", bookingId);
        var canceledBooking = bookingService.cancelBooking(bookingId);

        var responseBody =
                bookingMapper.mapFromBookingToBookingResponse(canceledBooking);

        log.trace("[BookingController@cancelBooking] - Booking canceled. Booking id: {}", bookingId);
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }
}
