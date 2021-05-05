package com.element.hikingTrail.trail.infrastructure.rest;

import com.element.hikingTrail.trail.application.TrailService;
import com.element.hikingTrail.trail.domain.Trail;
import com.element.hikingTrail.trail.infrastructure.database.TrailEntity;
import com.element.hikingTrail.trail.infrastructure.database.TrailRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;

@Slf4j
@RestController
@RequiredArgsConstructor
public class TrailController {

    private final TrailService trailService;
    private final TrailRepository trailRepository;

    @GetMapping("/trails")
    public ResponseEntity<TrailResponse> getTrails() {
        log.trace("[TrailController@getTrails] - Searching for available trails");

        var availableTrails = trailService.findAllTrails();

        var responseBody = TrailResponse.builder()
                .trails(availableTrails)
                .build();

        log.trace("[TrailController@getTrails] - Trails found: {}", availableTrails);
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

    @GetMapping("/trails/{trailName}")
    public ResponseEntity<TrailResponse> getTrail(@PathVariable String trailName) {
        log.trace("[TrailController@getTrails] - Searching for trail: {}", trailName);

        var trail = trailService.findByName(trailName);

        var responseBody = TrailResponse.builder()
                .trails(singletonList(trail))
                .build();

        log.trace("[TrailController@getTrails] - Trail found: {}", trail);
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

    @PostMapping("/trails/seed")
    public void seedDatabase() {
        TrailEntity shire = TrailEntity.builder()
                .name("Shire")
                .startAt("07:00")
                .endAt("09:00")
                .minimumAge(5)
                .maximumAge(100)
                .unitPrice(29.90)
                .build();
        TrailEntity gondor = TrailEntity.builder()
                .name("Gondor")
                .startAt("10:00")
                .endAt("13:00")
                .minimumAge(11)
                .maximumAge(50)
                .unitPrice(59.90)
                .build();
        TrailEntity mordor = TrailEntity.builder()
                .name("Mordor")
                .startAt("14:00")
                .endAt("19:00")
                .minimumAge(18)
                .maximumAge(40)
                .unitPrice(99.90)
                .build();
        trailRepository.save(shire);
        trailRepository.save(gondor);
        trailRepository.save(mordor);
    }
}
