package com.element.hikingTrail.trail.application;

import com.element.hikingTrail.trail.domain.Trail;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TrailService {

    private final TrailPersistencePort trailDatabaseAdapter;

    public List<Trail> findAllTrails() {
        List<Trail> all = trailDatabaseAdapter.findAll();

        return all;
    }

    public Trail findByName(String trailName) {
        return trailDatabaseAdapter.findByName(trailName);
    }
}
