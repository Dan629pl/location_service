package com.nordan.location;

import com.nordan.exception.LocationNotFoundException;
import com.nordan.exception.UnexpectedException;
import com.nordan.location.model.Location;
import com.nordan.userdevice.UserDeviceFacade;
import com.nordan.userdevice.model.UserDevice;
import lombok.RequiredArgsConstructor;

import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;

@RequiredArgsConstructor
class LocationService {

    private final LocationRepository deviceLocationRepository;
    private final LocationMapper deviceLocationMapper;
    private final UserDeviceFacade userDeviceFacade;

    Location update(UUID deviceId, Location location) {
        return Optional.of(deviceId)
                .map(userDeviceFacade::findByDeviceId)
                .map(UserDevice::getLocationId)
                .map(getLocation(location))
                .map(deviceLocationRepository::save)
                .map(deviceLocationMapper::map)
                .orElseThrow(() -> new UnexpectedException("Location update error"));
    }

    private Function<UUID, LocationEntity> getLocation(Location location) {
        return locationId -> deviceLocationRepository.findByLocationId(locationId)
                .map(updateLocation(location))
                .orElseGet(() -> LocationFactory.create(locationId, location));
    }

    private Function<LocationEntity, LocationEntity> updateLocation(Location location) {
        return entity -> entity
                .withLatitude(location.getLatitude())
                .withLongitude(location.getLongitude());
    }

    Location findByDeviceId(UUID deviceId) {
        return Optional.of(deviceId)
                .map(userDeviceFacade::findByDeviceId)
                .map(UserDevice::getLocationId)
                .flatMap(deviceLocationRepository::findByLocationId)
                .map(deviceLocationMapper::map)
                .orElseThrow(LocationNotFoundException::new);
    }

    void deleteByLocationId(UUID locationId) {
        deviceLocationRepository.deleteByLocationId(locationId);
    }
}
