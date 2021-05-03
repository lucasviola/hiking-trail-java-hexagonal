package com.element.hikingTrail.trail.infrastructure;

import com.element.hikingTrail.trail.domain.BookingStatus;
import com.element.hikingTrail.trail.domain.Booking;
import com.element.hikingTrail.trail.domain.Trail;
import com.element.hikingTrail.trail.infrastructure.database.BookingEntity;
import com.element.hikingTrail.trail.infrastructure.rest.BookingRequest;
import com.element.hikingTrail.trail.infrastructure.rest.BookingResponse;
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
        Trail trail = Trail.builder()
                .name("trailName")
                .endAt("12:00")
                .maximumAge(50)
                .minimumAge(10)
                .startAt("07:00")
                .unitPrice(150)
                .build();

        Booking actual = mapper.mapFromRequestToDomain(bookingRequest, trail);

        assertThat(actual.getTrail()).isEqualTo(trail);
    }

    @Test
    public void shouldMapBookingDomainToBookingEntity() {
        Trail trail = Trail.builder()
                .name("trailName")
                .endAt("12:00")
                .maximumAge(50)
                .minimumAge(10)
                .startAt("07:00")
                .unitPrice(150)
                .build();
        var booking = Booking.builder()
                .bookingId("id")
                .bookingStatus(BookingStatus.BOOKED.name())
                .trail(trail)
                .build();
        var expected = BookingEntity.builder()
                .bookingId("id")
                .bookingStatus(BookingStatus.BOOKED.name())
                .trail(trail)
                .build();

        BookingEntity actual = mapper.mapFromDomainToEntity(booking);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void shouldMapFromEntityToDomain() {
        Trail trail = Trail.builder()
                .name("trailName")
                .endAt("12:00")
                .maximumAge(50)
                .minimumAge(10)
                .startAt("07:00")
                .unitPrice(150)
                .build();
        var bookingEntity = BookingEntity.builder()
                .bookingId("id")
                .bookingStatus(BookingStatus.BOOKED.name())
                .trail(trail)
                .build();
        var expected = Booking.builder()
                .bookingId("id")
                .bookingStatus(BookingStatus.BOOKED.name())
                .trail(trail)
                .build();

        Booking actual = mapper.mapFromEntityToDomain(bookingEntity);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void shouldMapFromBookingDomainToBookingResponse() {
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
                .bookingStatus("status")
                .bookingId("id")
                .build();
        var expectedBookingResponse = BookingResponse.builder()
                .booking(booking)
                .build();

        BookingResponse actual = mapper.mapFromBookingToBookingResponse(booking);

        assertThat(actual).isEqualTo(expectedBookingResponse);
    }

}