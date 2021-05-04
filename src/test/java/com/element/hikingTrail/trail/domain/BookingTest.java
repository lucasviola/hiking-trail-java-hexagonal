package com.element.hikingTrail.trail.domain;

import org.junit.jupiter.api.Test;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

class BookingTest {

    @Test
    public void shouldReturnTrueIfAllHikersAreWithinAgeRangeOfTrail() {
        var hikers = asList(Hiker.builder()
                .name("Raul")
                .age(27)
                .build(), Hiker.builder()
                .name("Christine")
                .age(31)
                .build());
        var trail = Trail.builder()
                .name("trailName")
                .endAt("12:00")
                .maximumAge(50)
                .minimumAge(10)
                .startAt("07:00")
                .unitPrice(150)
                .build();
        Booking booking = Booking.builder()
                .trail(trail)
                .bookingDetails(BookingDetail.builder()
                        .hikers(hikers)
                        .build())
                .build();

        boolean areHikersWithinAgeRange =  booking.areHikersWithinAgeRange();

        assertThat(areHikersWithinAgeRange).isTrue();
    }

    @Test
    public void shouldReturnFalseIfOneHikerIsBelowMinimumAgeRequired() {
        var hikers = asList(Hiker.builder()
                .name("Raul")
                .age(9)
                .build(), Hiker.builder()
                .name("Christine")
                .age(31)
                .build());
        var trail = Trail.builder()
                .name("trailName")
                .endAt("12:00")
                .maximumAge(50)
                .minimumAge(10)
                .startAt("07:00")
                .unitPrice(150)
                .build();
        Booking booking = Booking.builder()
                .trail(trail)
                .bookingDetails(BookingDetail.builder()
                        .hikers(hikers)
                        .build())
                .build();

        boolean areHikersWithinAgeRange =  booking.areHikersWithinAgeRange();

        assertThat(areHikersWithinAgeRange).isFalse();
    }

    @Test
    public void shouldReturnFalseIfOneHikerIsAboveMaximumAgeRequired() {
        var hikers = asList(Hiker.builder()
                .name("Raul")
                .age(27)
                .build(), Hiker.builder()
                .name("Christine")
                .age(31)
                .build());
        var trail = Trail.builder()
                .name("trailName")
                .endAt("12:00")
                .maximumAge(30)
                .minimumAge(10)
                .startAt("07:00")
                .unitPrice(150)
                .build();
        Booking booking = Booking.builder()
                .trail(trail)
                .bookingDetails(BookingDetail.builder()
                        .hikers(hikers)
                        .build())
                .build();

        boolean areHikersWithinAgeRange =  booking.areHikersWithinAgeRange();

        assertThat(areHikersWithinAgeRange).isFalse();
    }

    @Test
    public void shouldReturnFalseIfOneHikerIsBelowAndOneIsAbove() {
        var hikers = asList(Hiker.builder()
                .name("Raul")
                .age(9)
                .build(), Hiker.builder()
                .name("Christine")
                .age(51)
                .build());
        var trail = Trail.builder()
                .name("trailName")
                .endAt("12:00")
                .maximumAge(50)
                .minimumAge(10)
                .startAt("07:00")
                .unitPrice(150)
                .build();
        Booking booking = Booking.builder()
                .trail(trail)
                .bookingDetails(BookingDetail.builder()
                        .hikers(hikers)
                        .build())
                .build();

        boolean areHikersWithinAgeRange =  booking.areHikersWithinAgeRange();

        assertThat(areHikersWithinAgeRange).isFalse();
    }

}