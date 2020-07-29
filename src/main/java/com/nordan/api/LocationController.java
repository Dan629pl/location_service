package com.nordan.api;

import com.nordan.location.LocationFacade;
import com.nordan.location.model.Location;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/locations")
@RequiredArgsConstructor
public class LocationController {

    private final LocationFacade deviceLocationFacade;

    @GetMapping("/{deviceId}")
    ResponseEntity<Location> findByDeviceId(@Valid @PathVariable UUID deviceId) {
        return ResponseEntity.ok(deviceLocationFacade.findByDeviceId(deviceId));
    }
    @PostMapping("/update/{deviceId}")
    ResponseEntity<Location> update(@Valid @PathVariable UUID deviceId, @RequestBody Location location) {
        return ResponseEntity.ok(deviceLocationFacade.update(deviceId, location));
    }
}
