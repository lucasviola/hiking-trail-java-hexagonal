package com.element.hikingTrail.trail.domain;

import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookingDetail {

    private List<Hiker> hikers;
}
