package com.nordan.location;

import com.nordan.userdevice.UserDeviceFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
class LocationConfiguration {

    private final UserDeviceFacade userDeviceFacade;

    LocationFacade testLocationFacade() {
        return locationFacade(new InMemoryLocationRepository());
    }

    @Bean
    LocationFacade locationFacade(LocationRepository locationRepository) {
        final var deviceLocationMapper = new LocationMapper();
        final var locationService = new LocationService(
                locationRepository,
                deviceLocationMapper,
                userDeviceFacade
        );
        return new LocationFacade(locationService);
    }
}
