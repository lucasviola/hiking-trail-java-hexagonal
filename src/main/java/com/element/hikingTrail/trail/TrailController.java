package com.element.hikingTrail.trail;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TrailController {

    private final TrailService trailService;
    private final TrailRepository trailRepository;

    @GetMapping("/trails")
    public ResponseEntity<TrailResponse> getAllTrails() {

        List<Trail> availableTrails = trailService.findAllTrails();

        var responseBody = TrailResponse.builder()
                .trails(availableTrails)
                .build();

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
