package com.nordan.location.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.validation.annotation.Validated;

@Getter
@Builder
@EqualsAndHashCode
@AllArgsConstructor
@Validated
public class Location {

    private final double latitude;
    private final double longitude;
}
