package com.element.hikingTrail.trail.infrastructure.rest;

import com.element.hikingTrail.trail.application.BookingService;
import com.element.hikingTrail.trail.domain.Booking;
import com.element.hikingTrail.trail.infrastructure.BookingMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class BookingController {
    private final BookingService bookingService;
    private final BookingMapper bookingMapper;

    @PostMapping("/booking")
    public ResponseEntity<BookingResponse> bookTrail(@RequestBody BookingRequest bookingRequest) {

        Booking booking = bookingMapper.mapFromRequestToDomain(bookingRequest);
        Booking bookedTrail = bookingService.bookTrail(booking);

        var responseBody =
                bookingMapper.mapFromBookingToBookingResponse(bookedTrail);

        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

    @GetMapping("/booking")
    public ResponseEntity<BookingResponse> getBooking(@RequestParam @NonNull String bookingId) {
        var bookedTrail = bookingService.findBooking(bookingId);

        var responseBody =
                bookingMapper.mapFromBookingToBookingResponse(bookedTrail);

        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }
}
