package com.element.hikingTrail.trail.infrastructure.database;

import com.element.hikingTrail.IntegrationTest;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
class BookingRepositoryTest extends IntegrationTest {

    private final BookingRepository bookingRepository;

    @Test
    public void shouldNotSaveSameBookingTwice() {
        String uniqueId = "id";
        BookingEntity saveOnce = BookingEntity.builder()
                .bookingId(uniqueId)
                .bookingStatus("status")
                .trailName("name")
                .build();
        BookingEntity update = BookingEntity.builder()
                .bookingId(uniqueId)
                .bookingStatus("status")
                .trailName("name")
                .build();
        bookingRepository.save(saveOnce);
        bookingRepository.save(update);

        List<BookingEntity> entities = bookingRepository.findAll();

        assertThat(entities.size()).isEqualTo(1);
        assertThat(entities.get(0).getBookingId()).isEqualTo(uniqueId);
    }

}