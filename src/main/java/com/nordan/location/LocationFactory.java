package com.nordan.location;

import com.nordan.location.model.Location;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.UUID;
@NoArgsConstructor(access = AccessLevel.PRIVATE)
class LocationFactory {

    static LocationEntity create(UUID locationId, Location location) {
        return LocationEntity.builder()
                .locationId(locationId)
                .latitude(location.getLatitude())
                .longitude(location.getLongitude())
                .build();
    }
}
