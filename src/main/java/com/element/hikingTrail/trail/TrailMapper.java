package com.element.hikingTrail.trail;

import com.element.hikingTrail.trail.domain.Trail;
import com.element.hikingTrail.trail.infrastructure.database.TrailEntity;
import org.mapstruct.Mapper;

@Mapper
public interface TrailMapper {

    Trail fromEntityToDomain(TrailEntity trailEntity);
}
