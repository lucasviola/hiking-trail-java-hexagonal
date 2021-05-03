package com.element.hikingTrail.trail.infrastructure;

import com.element.hikingTrail.trail.domain.*;
import com.element.hikingTrail.trail.infrastructure.database.BookingEntity;
import com.element.hikingTrail.trail.infrastructure.rest.BookingRequest;
import com.element.hikingTrail.trail.infrastructure.rest.BookingResponse;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;

class BookingMapperTest {

    private final BookingMapper mapper = Mappers.getMapper(BookingMapper.class);

    @Test
    public void shouldMapFromBookingRequestToDomain() {
        BookingRequest bookingRequest = BookingRequest.builder()
                .trailName("trailName")
                .bookingDetail(BookingDetail.builder()
                        .hikers(singletonList(Hiker.builder()
                                .name("Raul")
                                .age(27)
                                .build()))
                        .build())
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
        assertThat(actual.getHikers()).isEqualTo(bookingRequest.getBookingDetail().getHikers());
    }

    @Test
    public void shouldMapBookingDomainToBookingEntity() {
        var hikers = singletonList(Hiker.builder()
                .name("Raul")
                .age(27)
                .build());
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
                .hikers(hikers)
                .build();
        var expected = BookingEntity.builder()
                .bookingId("id")
                .bookingStatus(BookingStatus.BOOKED.name())
                .trail(trail)
                .hikers(hikers)
                .build();

        BookingEntity actual = mapper.mapFromDomainToEntity(booking);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void shouldMapFromEntityToDomain() {
        var hikers = singletonList(Hiker.builder()
                .name("Raul")
                .age(27)
                .build());
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
                .hikers(hikers)
                .build();
        var expected = Booking.builder()
                .bookingId("id")
                .bookingStatus(BookingStatus.BOOKED.name())
                .trail(trail)
                .hikers(hikers)
                .build();

        Booking actual = mapper.mapFromEntityToDomain(bookingEntity);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void shouldMapFromBookingDomainToBookingResponse() {
        var hikers = singletonList(Hiker.builder()
                .name("Raul")
                .age(27)
                .build());
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
                .hikers(hikers)
                .bookingId("id")
                .build();
        var expectedBookingResponse = BookingResponse.builder()
                .booking(booking)
                .build();

        BookingResponse actual = mapper.mapFromBookingToBookingResponse(booking);

        assertThat(actual).isEqualTo(expectedBookingResponse);
    }
}