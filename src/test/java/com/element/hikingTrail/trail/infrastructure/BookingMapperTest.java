package com.element.hikingTrail.trail.infrastructure;

import com.element.hikingTrail.trail.domain.BookingStatus;
import com.element.hikingTrail.trail.domain.Booking;
import com.element.hikingTrail.trail.infrastructure.database.BookingEntity;
import com.element.hikingTrail.trail.infrastructure.rest.BookingRequest;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.assertj.core.api.Assertions.assertThat;

class BookingMapperTest {

    private final BookingMapper mapper = Mappers.getMapper(BookingMapper.class);

    @Test
    public void shouldMapTrailNameFromBookingRequestToDomain() {
        BookingRequest bookingRequest = BookingRequest.builder()
                .trailName("trailName")
                .build();

        Booking actual = mapper.mapFromRequestToDomain(bookingRequest);

        assertThat(actual.getTrailName()).isEqualTo("trailName");
    }

    @Test
    public void shouldMapBookingDomainToBookingEntity() {
        var booking = Booking.builder()
                .bookingId("id")
                .bookingStatus(BookingStatus.BOOKED.name())
                .trailName("name")
                .build();
        var expected = BookingEntity.builder()
                .bookingId("id")
                .bookingStatus(BookingStatus.BOOKED.name())
                .trailName("name")
                .build();

        BookingEntity actual = mapper.mapFromDomainToEntity(booking);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void shouldMapFromEntityToDomain() {
        var bookingEntity = BookingEntity.builder()
                .bookingId("id")
                .bookingStatus(BookingStatus.BOOKED.name())
                .trailName("name")
                .build();
        var expected = Booking.builder()
                .bookingId("id")
                .bookingStatus(BookingStatus.BOOKED.name())
                .trailName("name")
                .build();

        Booking actual = mapper.mapFromEntityToDomain(bookingEntity);

        assertThat(actual).isEqualTo(expected);
    }

}