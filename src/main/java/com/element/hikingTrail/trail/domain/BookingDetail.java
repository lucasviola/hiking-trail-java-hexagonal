package com.element.hikingTrail.trail.domain;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@Builder
@RequiredArgsConstructor
public class BookingDetail {

    private final List<Hiker> hikers;
}
