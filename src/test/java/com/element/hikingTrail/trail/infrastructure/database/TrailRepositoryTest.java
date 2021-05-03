package com.element.hikingTrail.trail.infrastructure.database;

import com.element.hikingTrail.IntegrationTest;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


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
        var expected = Optional.of(testEntity);

        var actual = trailRepository.findTrailEntityByName("testName");

        assertThat(actual).isEqualTo(expected);
    }
}