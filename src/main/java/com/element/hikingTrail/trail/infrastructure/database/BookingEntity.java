package com.element.hikingTrail.trail.infrastructure.database;

import com.element.hikingTrail.trail.domain.Hiker;
import com.element.hikingTrail.trail.domain.Trail;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "bookings")
public class BookingEntity {

    @Id
    private String bookingId;
    private Trail trail;
    private List<Hiker> hikers;
    private String bookingStatus;
}
