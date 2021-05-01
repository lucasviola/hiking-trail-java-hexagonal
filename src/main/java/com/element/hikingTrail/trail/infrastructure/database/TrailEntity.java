package com.element.hikingTrail.trail.infrastructure.database;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Data
@Builder
@Document(collection = "trails")
@NoArgsConstructor
@AllArgsConstructor
public class TrailEntity {

    @Id
    private String id;
    private String name;
    private String startAt;
    private String endAt;
    private int minimumAge;
    private int maximumAge;
    private double unitPrice;
}
