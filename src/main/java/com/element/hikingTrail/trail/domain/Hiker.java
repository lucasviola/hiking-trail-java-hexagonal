package com.element.hikingTrail.trail.domain;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Hiker {
    private String name;
    private int age;
}
