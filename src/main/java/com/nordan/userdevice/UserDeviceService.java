package com.nordan.userdevice;

import com.nordan.exception.DeviceNotFoundException;
import com.nordan.exception.UnexpectedException;
import com.nordan.location.LocationFacade;
import com.nordan.userdevice.model.UserDevice;
import lombok.RequiredArgsConstructor;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
public class UserDeviceService {

    private final UserDeviceRepository userDeviceRepository;
    private final UserDeviceMapper userDeviceMapper;
    private final LocationFacade locationFacade;

    UserDevice register(UserDevice userDevice) {
        return Optional.of(userDevice)
                .map(UserDeviceFactory::create)
                .map(userDeviceRepository::save)
                .map(userDeviceMapper::map)
                .orElseThrow(() -> new UnexpectedException("Registration error"));
    }

    void deleteByDeviceId(UUID deviceId) {
        userDeviceRepository.findByDeviceId(deviceId)
                .map(UserDeviceEntity::getLocationId)
                .ifPresent(locationFacade::deleteByLocationId);
        userDeviceRepository.deleteByDeviceId(deviceId);
    }

    UserDevice findByDeviceId(UUID deviceId) {
        return userDeviceRepository.findByDeviceId(deviceId)
                .map(userDeviceMapper::map)
                .orElseThrow(DeviceNotFoundException::new);
    }
}
