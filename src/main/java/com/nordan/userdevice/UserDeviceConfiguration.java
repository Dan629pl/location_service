package com.nordan.userdevice;

import com.nordan.location.LocationFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
@RequiredArgsConstructor
class UserDeviceConfiguration {

    private final LocationFacade locationFacade;

    UserDeviceFacade testUserDeviceFacade() {
        return userDeviceFacade(new InMemoryUserDeviceRepository());
    }

    @Bean
    UserDeviceFacade userDeviceFacade(UserDeviceRepository userDeviceRepository) {
        final var mapper = new UserDeviceMapper();
        final var userDeviceService = new UserDeviceService(
                userDeviceRepository,
                mapper,
                locationFacade
        );
        return new UserDeviceFacade(userDeviceService);
    }
}
