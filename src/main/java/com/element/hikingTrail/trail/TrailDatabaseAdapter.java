package com.element.hikingTrail.trail;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
@RequiredArgsConstructor
public class TrailDatabaseAdapter {

    private final TrailRepository trailRepository;
    private final TrailMapper trailMapper;

    public List<Trail> findAll() {

        var trailEntities = trailRepository.findAll();

        return trailEntities.stream()
                .map(trailMapper::fromEntityToDomain)
                .collect(toList());
    }
}
