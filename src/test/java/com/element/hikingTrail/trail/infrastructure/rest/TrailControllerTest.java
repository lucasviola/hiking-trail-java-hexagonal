package com.element.hikingTrail.trail.infrastructure.rest;

import com.element.hikingTrail.IntegrationTest;
import com.element.hikingTrail.trail.domain.Trail;
import com.element.hikingTrail.trail.infrastructure.database.TrailEntity;
import com.element.hikingTrail.trail.infrastructure.database.TrailRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
class TrailControllerTest extends IntegrationTest {

    private final TrailController trailController;
    private final TrailRepository trailRepository;

    @BeforeEach
    public void setUp() {
        trailRepository.deleteAll();
        TrailEntity firstTrail = TrailEntity.builder()
                .name("firstTrail")
                .build();
        TrailEntity secondTrail = TrailEntity.builder()
                .name("secondTrail")
                .build();
        trailRepository.saveAll(asList(firstTrail, secondTrail));
    }

    @Test
    public void whenGetTrails_returnsListOfAvailableTrails() {
        var expectedResponse = TrailResponse.builder()
                .trails(asList(Trail.builder()
                        .name("firstTrail")
                        .build(), Trail.builder()
                        .name("secondTrail")
                        .build()))
                .build();

        ResponseEntity<TrailResponse> response = trailController.getTrails(null);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).usingRecursiveComparison()
                .isEqualTo(expectedResponse);
    }

    @Test
    public void whenGetTrail_withTrailNameAsRequestParam_shouldReturnSpecificTrail() {
        var expectedResponse = TrailResponse.builder()
                .trails(singletonList(Trail.builder()
                        .name("firstTrail")
                        .build()))
                .build();

        ResponseEntity<TrailResponse> response = trailController.getTrails("firstTrail");

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).usingRecursiveComparison()
                .isEqualTo(expectedResponse);
    }
}