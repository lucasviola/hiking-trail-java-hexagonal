package com.element.hikingTrail.trail.infrastructure.database;

import com.element.hikingTrail.IntegrationTest;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@RequiredArgsConstructor(onConstructor = @__(@Autowired))
class TrailRepositoryTest extends IntegrationTest {

    private final TrailRepository trailRepository;

    @Test
    public void shouldFindTrailEntityByName() {
        trailRepository.deleteAll();
        TrailEntity testEntity = TrailEntity.builder()
                .name("testName")
                .build();
        trailRepository.save(testEntity);
        var expected = singletonList(testEntity);

        List<TrailEntity> actual = trailRepository.findTrailEntityByName("testName");

        assertThat(actual).isEqualTo(expected);
    }
}