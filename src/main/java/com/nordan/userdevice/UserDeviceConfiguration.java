package com.nordan.userdevice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class UserDeviceConfiguration {

    UserDeviceFacade testUserDeviceFacade() {
        return userDeviceFacade(new InMemoryUserDeviceRepository());
    }

    @Bean
    UserDeviceFacade userDeviceFacade(UserDeviceRepository userDeviceRepository) {
        final var mapper = new UserDeviceMapper();
        final var userDeviceService = new UserDeviceService(userDeviceRepository, mapper);
        return new UserDeviceFacade(userDeviceService);
    }
}
