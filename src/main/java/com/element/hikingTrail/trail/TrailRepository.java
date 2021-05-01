package com.element.hikingTrail.trail;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrailRepository extends MongoRepository<TrailEntity, String> {
}
