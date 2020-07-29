package com.nordan.api;

import com.nordan.location.model.Location;
import com.nordan.userdevice.UserDeviceFacade;
import com.nordan.userdevice.model.UserDevice;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;


@RestController
@RequestMapping("/user-devices")
@RequiredArgsConstructor
public class UserDeviceController {

    private final UserDeviceFacade userDeviceFacade;

    @PostMapping("/register")
    ResponseEntity<UserDevice> register(@Valid @RequestBody UserDevice userDevice) {
        return ResponseEntity.ok(userDeviceFacade.register(userDevice));
    }

    @PostMapping("/delete/{deviceId}")
    ResponseEntity<?> delete(@PathVariable UUID deviceId) {
        userDeviceFacade.delete(deviceId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{deviceId}")
    ResponseEntity<UserDevice> findBy(@Valid @PathVariable UUID deviceId) {
        return ResponseEntity.ok(userDeviceFacade.findByDeviceId(deviceId));
    }
}
