package com.element.hikingTrail.trail.infrastructure.database;

import com.element.hikingTrail.trail.application.exception.BookingNotFound;
import com.element.hikingTrail.trail.application.exception.TrailNotFound;
import com.element.hikingTrail.trail.infrastructure.TrailMapper;
import com.element.hikingTrail.trail.domain.Trail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class TrailDatabaseAdapterTest {

    @Mock
    private TrailRepository trailRepository;
    @Spy
    private final TrailMapper mapper = Mappers.getMapper(TrailMapper.class);
    @InjectMocks
    private TrailDatabaseAdapter trailDatabaseAdapter;

    private List<TrailEntity> entities;

    @BeforeEach
    public void setUp() {
        this.entities = asList(TrailEntity.builder()
                .name("Mordor")
                .build(), TrailEntity.builder()
                .name("Shire")
                .build());
    }


    @Test
    public void shouldRetrieveTrailsFromDatabaseAndMapToTrailDomain() {
        List<Trail> expectedTrails = asList(Trail.builder()
                .name("Mordor")
                .build(), Trail.builder()
                .name("Shire")
                .build());
        Mockito.when(trailRepository.findAll())
                .thenReturn(entities);

        List<Trail> actualTrails = trailDatabaseAdapter.findAll();

        assertThat(actualTrails).isEqualTo(expectedTrails);
    }

    @Test
    public void shouldFindTrailEntityByNameAndMapToTrailDomain() {
        Trail expectedTrails = Trail.builder()
                .name("Mordor")
                .build();
        Mockito.when(trailRepository.findTrailEntityByName("Mordor"))
                .thenReturn(Optional.of(entities.get(0)));

        Trail actualTrails = trailDatabaseAdapter.findByName("Mordor");

        assertThat(actualTrails).isEqualTo(expectedTrails);
    }

    @Test
    public void shouldThrowTrailNotFoundExceptionWhenThereIsNoTrailWithName() {
        Mockito.when(trailRepository.findTrailEntityByName("Mordor"))
                .thenReturn(Optional.empty());
        Exception exception = assertThrows(TrailNotFound.class,
                () -> trailDatabaseAdapter.findByName("Mordor"));
        String expectedMessage = "Trail Mordor not found";

        String actualMessage = exception.getMessage();

        assertThat(actualMessage).isEqualTo(expectedMessage);
    }

}