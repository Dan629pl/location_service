package com.nordan.location;

import com.nordan.userdevice.UserDeviceFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
class LocationConfiguration {

    private final UserDeviceFacade userDeviceFacade;

    @Bean
    LocationFacade deviceLocationFacade(LocationRepository deviceLocationRepository) {

        final var deviceLocationMapper = new LocationMapper();
        final var deviceLocationService = new LocationService(
                deviceLocationRepository,
                deviceLocationMapper,
                userDeviceFacade
        );
        return new LocationFacade(deviceLocationService);
    }
}
