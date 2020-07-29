package com.nordan.userdevice;

import com.nordan.userdevice.model.UserDevice;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class UserDeviceFactory {

    static UserDeviceEntity create(UserDevice userDevice) {
        return UserDeviceEntity.builder()
                .deviceId(UUID.randomUUID())
                .locationId(UUID.randomUUID())
                .username(userDevice.getUsername())
                .email(userDevice.getEmail())
                .manufacturer(userDevice.getManufacturer())
                .model(userDevice.getModel())
                .build();
    }
}
