package com.element.hikingTrail.trail.infrastructure.rest;

import com.element.hikingTrail.IntegrationTest;
import com.element.hikingTrail.trail.infrastructure.database.TrailEntity;
import com.element.hikingTrail.trail.infrastructure.database.TrailRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.assertj.core.api.Assertions.assertThat;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TrailControllerTest extends IntegrationTest {

    private final TrailRepository trailRepository;
    private final MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        TrailEntity trailEntity = TrailEntity.builder()
                .name("Shire")
                .endAt("12:00")
                .maximumAge(50)
                .minimumAge(10)
                .startAt("07:00")
                .unitPrice(150)
                .build();
        trailRepository.save(trailEntity);
    }

    @Test
    @SneakyThrows
    public void whenGetTrails_shouldReturnListOfAvailableTrails() {
        String expectedResponse = "{\"trails\":[{\"name\":\"Shire\",\"startAt\":\"07:00\",\"endAt\":\"12:00\",\"minimumAge\":10,\"maximumAge\":50,\"unitPrice\":150.0}]}";

        String response = mockMvc.perform(MockMvcRequestBuilders.get("/trails")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn().getResponse().getContentAsString();

        assertThat(response).isEqualTo(expectedResponse);
    }

    @Test
    @SneakyThrows
    public void whenGetTrails_withTrailNameInPath_shouldReturnTrail() {
        String expectedResponse = "{\"trails\":[{\"name\":\"Shire\",\"startAt\":\"07:00\",\"endAt\":\"12:00\",\"minimumAge\":10,\"maximumAge\":50,\"unitPrice\":150.0}]}";

        String response = mockMvc.perform(MockMvcRequestBuilders.get("/trails/Shire")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn().getResponse().getContentAsString();

        assertThat(response).isEqualTo(expectedResponse);
    }

    @Test
    @SneakyThrows
    public void whenGetTrails_withTrailNameInPath_andTrailDoesNotExist_shouldReturnNotFound() {
        mockMvc.perform(MockMvcRequestBuilders.get("/trails/InvalidTrail")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$.errorMessage",
                        Is.is("Trail InvalidTrail not found")))
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON));
    }

    @AfterEach
    public void tearDown() {
        trailRepository.deleteAll();
    }
}
