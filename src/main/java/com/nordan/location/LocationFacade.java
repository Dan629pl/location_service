package com.nordan.location;

import com.nordan.location.model.Location;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.UUID;

@RequiredArgsConstructor
public class LocationFacade {

    private final LocationService locationService;

    @Transactional
    public Location update(@Valid UUID deviceId, @Valid Location location) {
        return locationService.update(deviceId, location);
    }

    public Location findByDeviceId(UUID deviceId) {
        return locationService.findByDeviceId(deviceId);
    }

    @Transactional
    public void deleteByLocationId(UUID locationId) {
        locationService.deleteByLocationId(locationId);
    }
}
