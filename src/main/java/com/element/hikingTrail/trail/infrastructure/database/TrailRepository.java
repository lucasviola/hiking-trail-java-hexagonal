package com.element.hikingTrail.trail.infrastructure.database;

import com.element.hikingTrail.trail.domain.Trail;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrailRepository extends MongoRepository<TrailEntity, String> {

    List<TrailEntity> findTrailEntityByName(String name);
}
