package com.nordan.userdevice;

import com.nordan.userdevice.model.UserDevice;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.UUID;

@RequiredArgsConstructor
public class UserDeviceFacade {
    private final UserDeviceService userDeviceService;

    @Transactional
    public UserDevice register(@Valid UserDevice userDevice) {
        return userDeviceService.register(userDevice);
    }

    public UserDevice findByDeviceId(UUID deviceId) {
        return userDeviceService.findByDeviceId(deviceId);
    }

    @Transactional
    public void delete(UUID deviceId) {
         userDeviceService.delete(deviceId);
    }
}
