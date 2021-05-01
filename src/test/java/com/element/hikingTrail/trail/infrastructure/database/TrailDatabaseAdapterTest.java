package com.element.hikingTrail.trail.infrastructure.database;

import com.element.hikingTrail.IntegrationTest;
import com.element.hikingTrail.trail.domain.Trail;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
class TrailDatabaseAdapterTest extends IntegrationTest{

    private final TrailRepository trailRepository;
    private final TrailDatabaseAdapter trailDatabaseAdapter;


    @Test
    public void shouldRetrieveTrailsFromDatabaseAndMapToDomain() {
        trailRepository.deleteAll();
        TrailEntity entity = TrailEntity.builder()
                .name("Mordor")
                .startAt("14:00")
                .endAt("19:00")
                .minimumAge(18)
                .maximumAge(40)
                .unitPrice(99.90)
                .build();
        trailRepository.save(entity);
        List<Trail> expectedTrails = singletonList(Trail.builder()
                .name("Mordor")
                .startAt("14:00")
                .endAt("19:00")
                .minimumAge(18)
                .maximumAge(40)
                .unitPrice(99.90)
                .build());

        List<Trail> actualTrails = trailDatabaseAdapter.findAll();

        assertThat(actualTrails).isEqualTo(expectedTrails);
    }

}