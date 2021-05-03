package com.element.hikingTrail.trail.infrastructure;

import com.element.hikingTrail.trail.domain.Booking;
import com.element.hikingTrail.trail.domain.Trail;
import com.element.hikingTrail.trail.infrastructure.database.BookingEntity;
import com.element.hikingTrail.trail.infrastructure.rest.BookingRequest;
import com.element.hikingTrail.trail.infrastructure.rest.BookingResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface BookingMapper {

    Booking mapFromRequestToDomain(BookingRequest bookingRequest, Trail trail);
    BookingEntity mapFromDomainToEntity(Booking booking);
    Booking mapFromEntityToDomain(BookingEntity saved);
    BookingResponse mapFromBookingToBookingResponse(Booking booking);
}
