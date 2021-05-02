package com.element.hikingTrail.trail.infrastructure;

import com.element.hikingTrail.trail.domain.Booking;
import com.element.hikingTrail.trail.infrastructure.database.BookingEntity;
import com.element.hikingTrail.trail.infrastructure.rest.BookingRequest;
import org.mapstruct.Mapper;

@Mapper
public interface BookingMapper {

    Booking mapFromRequestToDomain(BookingRequest bookingRequest);
    BookingEntity mapFromDomainToEntity(Booking booking);
    Booking mapFromEntityToDomain(BookingEntity saved);
}
