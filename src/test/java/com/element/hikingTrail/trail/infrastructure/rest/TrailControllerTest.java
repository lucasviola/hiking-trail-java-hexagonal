package com.element.hikingTrail.trail.infrastructure.rest;

import com.element.hikingTrail.IntegrationTest;
import com.element.hikingTrail.trail.domain.Trail;
import com.element.hikingTrail.trail.infrastructure.database.TrailEntity;
import com.element.hikingTrail.trail.infrastructure.database.TrailRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
class TrailControllerTest extends IntegrationTest {

    private final TrailController trailController;
    private final TrailRepository trailRepository;

    @Test
    public void whenGetTrails_returnsListOfAvailableTrails() {
        trailRepository.deleteAll();
        TrailEntity testTrail = TrailEntity.builder()
                .name("TestTrail")
                .build();
        trailRepository.save(testTrail);
        var expectedResponse = TrailResponse.builder()
                .trails(singletonList(Trail.builder()
                        .name("TestTrail")
                        .build()))
                .build();

        ResponseEntity<TrailResponse> response = trailController.getAllTrails();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).usingRecursiveComparison()
                .isEqualTo(expectedResponse);
    }
}