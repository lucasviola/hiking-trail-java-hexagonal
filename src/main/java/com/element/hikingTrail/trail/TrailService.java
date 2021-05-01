package com.element.hikingTrail.trail;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TrailService {

    private final TrailDatabaseAdapter trailDatabaseAdapter;

    public List<Trail> findAllTrails() {
        List<Trail> all = trailDatabaseAdapter.findAll();

        return all;
    }
}
