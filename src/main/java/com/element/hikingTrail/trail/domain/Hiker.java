package com.element.hikingTrail.trail.domain;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
public class Hiker {
    private final String name;
    private final int age;
}
