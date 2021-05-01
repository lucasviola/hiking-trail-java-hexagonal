package com.element.hikingTrail.trail;

import lombok.Builder;

import java.util.List;

@Builder
public class TrailResponse {
    private final List<Trail> trails;
}
