package com.element.hikingTrail.trail;

import org.mapstruct.Mapper;

@Mapper
public interface TrailMapper {

    Trail fromEntityToDomain(TrailEntity trailEntity);
}
