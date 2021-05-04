package com.element.hikingTrail.trail.infrastructure.database;

import com.element.hikingTrail.trail.application.exception.TrailNotFound;
import com.element.hikingTrail.trail.infrastructure.TrailMapper;
import com.element.hikingTrail.trail.application.TrailPersistencePort;
import com.element.hikingTrail.trail.domain.Trail;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
@RequiredArgsConstructor
public class TrailDatabaseAdapter implements TrailPersistencePort {

    private final TrailRepository trailRepository;
    private final TrailMapper trailMapper;

    @Override
    public List<Trail> findAll() {
        var trailEntities = trailRepository.findAll();

        return trailEntities.stream()
                .map(trailMapper::fromEntityToDomain)
                .collect(toList());
    }

    @Override
    @SneakyThrows
    public Trail findByName(String name) {
        var trail =
                trailRepository.findTrailEntityByName(name)
                        .orElseThrow(() -> new TrailNotFound("Trail "+ name +" not found"));

        return trailMapper.fromEntityToDomain(trail);
    }
}
