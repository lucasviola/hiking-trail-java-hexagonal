package com.element.hikingTrail.trail.infrastructure.rest;

import com.element.hikingTrail.IntegrationTest;
import com.element.hikingTrail.trail.domain.BookingDetail;
import com.element.hikingTrail.trail.domain.BookingStatus;
import com.element.hikingTrail.trail.domain.Hiker;
import com.element.hikingTrail.trail.domain.Trail;
import com.element.hikingTrail.trail.infrastructure.database.BookingEntity;
import com.element.hikingTrail.trail.infrastructure.database.BookingRepository;
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

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BookingControllerTest extends IntegrationTest {

    private final BookingRepository bookingRepository;
    private final TrailRepository trailRepository;
    private final BookingController bookingController;
    private final MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        var hikers = singletonList(Hiker.builder()
                .name("Raul")
                .age(27)
                .build());
        TrailEntity trailEntity = TrailEntity.builder()
                .name("Shire")
                .endAt("12:00")
                .maximumAge(50)
                .minimumAge(10)
                .startAt("07:00")
                .unitPrice(150)
                .build();
        String uniqueId = "id";
        BookingEntity bookingEntity = BookingEntity.builder()
                .bookingId(uniqueId)
                .bookingStatus("status")
                .trail(Trail.builder()
                        .name("Shire")
                        .endAt("12:00")
                        .maximumAge(50)
                        .minimumAge(10)
                        .startAt("07:00")
                        .unitPrice(150)
                        .build())
                .bookingDetails(BookingDetail.builder()
                        .hikers(hikers)
                        .build())
                .build();
        trailRepository.save(trailEntity);
        bookingRepository.save(bookingEntity);
    }

    @Test
    @SneakyThrows
    public void whenPostRequestToBooking_thenReturnsBookingResponse() {
        String request = "{\n" +
                "  \"trailName\": \"Shire\",\n" +
                "  \"bookingDetails\": {\n" +
                "    \"hikers\": [\n" +
                "      {\n" +
                "        \"name\": \"Raul\",\n" +
                "        \"age\": 27\n" +
                "      }\n" +
                "    ]\n" +
                "  }  \n" +
                "}";

        mockMvc.perform(MockMvcRequestBuilders.post("/booking")
                .content(request)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.jsonPath("$.booking.bookingStatus",
                        Is.is(BookingStatus.BOOKED.name())))
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @SneakyThrows
    public void whenPostRequestToBooking_withNonExistingTrail_shouldReturnNotFound() {
        String request = "{\n" +
                "  \"trailName\": \"InvalidTrail\",\n" +
                "  \"bookingDetails\": {\n" +
                "    \"hikers\": [\n" +
                "      {\n" +
                "        \"name\": \"Raul\",\n" +
                "        \"age\": 27\n" +
                "      }\n" +
                "    ]\n" +
                "  }  \n" +
                "}";

        mockMvc.perform(MockMvcRequestBuilders.post("/booking")
                .content(request)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.jsonPath("$.errorMessage",
                        Is.is("Trail InvalidTrail not found")))
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @SneakyThrows
    public void whenPostRequestToBooking_withHikerNotEligible_shouldReturnBadRequest() {
        String request = "{\n" +
                "  \"trailName\": \"Shire\",\n" +
                "  \"bookingDetails\": {\n" +
                "    \"hikers\": [\n" +
                "      {\n" +
                "        \"name\": \"Jose\",\n" +
                "        \"age\": 8\n" +
                "      }\n" +
                "    ]\n" +
                "  }  \n" +
                "}";

        mockMvc.perform(MockMvcRequestBuilders.post("/booking")
                .content(request)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.jsonPath("$.errorMessage",
                        Is.is("One or more hikers is above or below age limit")))
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON));
    }

    @SneakyThrows
    @Test
    public void whenGetBookingWithBookingId_shouldReturnBookingInTheResponseBody() {
        String expectedResponse = "{\"booking\":{\"bookingId\":\"id\",\"trail\":{\"name\":\"Shire\",\"startAt\":\"07:00\",\"endAt\":\"12:00\",\"minimumAge\":10,\"maximumAge\":50,\"unitPrice\":150.0},\"bookingDetails\":{\"hikers\":[{\"name\":\"Raul\",\"age\":27}]},\"bookingStatus\":\"status\"}}";

        String response = mockMvc.perform(MockMvcRequestBuilders.get("/booking/id")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn().getResponse().getContentAsString();

        assertThat(response).isEqualTo(expectedResponse);
    }

    @Test
    @SneakyThrows
    public void whenDeleteRequestToCancelBooking_shouldReturnBookingWithStatusCanceled() {
        String expectedResponse = "{\"booking\":{\"bookingId\":\"id\",\"trail\":{\"name\":\"Shire\",\"startAt\":\"07:00\",\"endAt\":\"12:00\",\"minimumAge\":10,\"maximumAge\":50,\"unitPrice\":150.0},\"bookingDetails\":{\"hikers\":[{\"name\":\"Raul\",\"age\":27}]},\"bookingStatus\":\"CANCELED\"}}";

        String response = mockMvc.perform(MockMvcRequestBuilders.delete("/booking/id")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn().getResponse().getContentAsString();

        assertThat(response).isEqualTo(expectedResponse);
    }

    @AfterEach
    public void tearDown() {
        trailRepository.deleteAll();
        bookingRepository.deleteAll();
    }

}
