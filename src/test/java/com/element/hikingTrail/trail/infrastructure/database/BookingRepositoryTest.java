package com.element.hikingTrail.trail.infrastructure.database;

import com.element.hikingTrail.IntegrationTest;
import com.element.hikingTrail.trail.domain.Hiker;
import com.element.hikingTrail.trail.domain.Trail;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
class BookingRepositoryTest extends IntegrationTest {

    private final BookingRepository bookingRepository;

    @Test
    public void shouldNotSaveSameBookingTwice() {
        bookingRepository.deleteAll();
        var hikers = singletonList(Hiker.builder()
                .name("Raul")
                .age(27)
                .build());
        Trail trail = Trail.builder()
                .name("trailName")
                .endAt("12:00")
                .maximumAge(50)
                .minimumAge(10)
                .startAt("07:00")
                .unitPrice(150)
                .build();
        String uniqueId = "id";
        BookingEntity firstEntity = BookingEntity.builder()
                .bookingId(uniqueId)
                .bookingStatus("status")
                .trail(trail)
                .hikers(hikers)
                .build();
        BookingEntity duplicatedEntity = BookingEntity.builder()
                .bookingId(uniqueId)
                .bookingStatus("status")
                .trail(trail)
                .hikers(hikers)
                .build();
        bookingRepository.save(firstEntity);
        bookingRepository.save(duplicatedEntity);

        List<BookingEntity> entities = bookingRepository.findAll();

        assertThat(entities.size()).isEqualTo(1);
        assertThat(entities.get(0)).isEqualTo(firstEntity);
    }

}