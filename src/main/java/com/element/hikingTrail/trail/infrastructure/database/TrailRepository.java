package com.element.hikingTrail.trail.infrastructure.database;

import com.element.hikingTrail.trail.domain.Trail;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TrailRepository extends MongoRepository<TrailEntity, String> {

    Optional<TrailEntity> findTrailEntityByName(String name);
}
