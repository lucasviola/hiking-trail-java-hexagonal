package com.element.hikingTrail.trail.application;

import com.element.hikingTrail.trail.domain.Trail;

import java.util.List;

public interface TrailPersistencePort {

    List<Trail> findAll();
    Trail findByName(String trailName);

}
