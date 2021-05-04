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

    @GetMapping("/booking")
    public ResponseEntity<BookingResponse> getBooking(@RequestParam @NonNull String bookingId) {
        var bookedTrail = bookingService.findBooking(bookingId);

        var responseBody =
                bookingMapper.mapFromBookingToBookingResponse(bookedTrail);

        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

    @DeleteMapping("/booking")
    public ResponseEntity<BookingResponse> cancelBooking(@RequestParam @NonNull String bookingId) {
        var canceledBooking = bookingService.cancelBooking(bookingId);

        var responseBody =
                bookingMapper.mapFromBookingToBookingResponse(canceledBooking);

        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }
}
