package com.element.hikingTrail.trail.infrastructure.rest;

import com.element.hikingTrail.trail.domain.Trail;
import lombok.*;

import java.util.List;

@Data
@Builder
@RequiredArgsConstructor
public class TrailResponse {
    private final List<Trail> trails;
}
