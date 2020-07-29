package com.nordan.location;

import com.nordan.exception.LocationNotFoundException;
import com.nordan.exception.UnexpectedException;
import com.nordan.location.model.Location;
import com.nordan.userdevice.UserDeviceFacade;
import com.nordan.userdevice.model.UserDevice;
import lombok.RequiredArgsConstructor;

import javax.validation.Valid;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
class LocationService {

    private final LocationRepository deviceLocationRepository;
    private final LocationMapper deviceLocationMapper;
    private final UserDeviceFacade userDeviceFacade;

    Location update(@Valid UUID deviceId, Location location) {
        return Optional.of(deviceId)
                .map(userDeviceFacade::findByDeviceId)
                .map(UserDevice::getLocationId)
                .map(locationId -> createLocation(locationId, location))
                .map(deviceLocationRepository::save)
                .map(deviceLocationMapper::map)
                .orElseThrow(() -> new UnexpectedException("Błąd aktualizacji lokalizacji"));
    }

    private LocationEntity createLocation(UUID locationId, Location location) {
        return LocationFactory.create(locationId, location);
    }

    public Location findByDeviceId(UUID deviceId) {
        return Optional.of(deviceId)
                .map(userDeviceFacade::findByDeviceId)
                .map(UserDevice::getLocationId)
                .flatMap(deviceLocationRepository::findByLocationId)
                .map(deviceLocationMapper::map)
                .orElseThrow(LocationNotFoundException::new);

    }
}
