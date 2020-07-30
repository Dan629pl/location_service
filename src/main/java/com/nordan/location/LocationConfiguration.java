package com.nordan.location;

import com.nordan.userdevice.UserDeviceFacade;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
class LocationConfiguration {

    private final UserDeviceFacade userDeviceFacade;

    LocationConfiguration(@Lazy UserDeviceFacade userDeviceFacade) {
        this.userDeviceFacade = userDeviceFacade;
    }

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
