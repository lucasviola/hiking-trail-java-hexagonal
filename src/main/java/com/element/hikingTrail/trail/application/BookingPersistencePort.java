package com.element.hikingTrail.trail.application;

import com.element.hikingTrail.trail.domain.Booking;

public interface BookingPersistencePort {
    Booking saveBooking(Booking booking);
}
