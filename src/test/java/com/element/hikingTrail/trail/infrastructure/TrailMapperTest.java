package com.element.hikingTrail.trail.infrastructure;

import com.element.hikingTrail.trail.domain.Trail;
import com.element.hikingTrail.trail.infrastructure.database.TrailEntity;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.assertj.core.api.Assertions.assertThat;

class TrailMapperTest {

    private final TrailMapper mapper = Mappers.getMapper(TrailMapper.class);

    @Test
    public void shouldMapFromTrailEntitytToDomain() {
        Trail trail = Trail.builder()
                .name("Mordor")
                .startAt("14:00")
                .endAt("19:00")
                .minimumAge(18)
                .maximumAge(40)
                .unitPrice(99.90)
                .build();
        TrailEntity trailEntity = TrailEntity.builder()
                .name("Mordor")
                .startAt("14:00")
                .endAt("19:00")
                .minimumAge(18)
                .maximumAge(40)
                .unitPrice(99.90)
                .build();

        Trail actualTrail = mapper.fromEntityToDomain(trailEntity);

        assertThat(actualTrail).isEqualTo(trail);
    }
}