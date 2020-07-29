package com.nordan.userdevice;

import com.nordan.userdevice.model.UserDevice;

class UserDeviceMapper {

    UserDevice map(UserDeviceEntity entity) {
        return UserDevice.builder()
                .deviceId(entity.getDeviceId())
                .email(entity.getEmail())
                .locationId(entity.getLocationId())
                .manufacturer(entity.getManufacturer())
                .model(entity.getModel())
                .username(entity.getUsername())
                .build();
    }
}
