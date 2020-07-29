package com.nordan.userdevice;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.UUID;

interface UserDeviceRepository extends Repository<UserDeviceEntity, Long> {

    @Modifying
    @Query(value = "DELETE FROM USER_DEVICE D WHERE D.DEVICE_ID = :deviceId", nativeQuery = true)
    void deleteByDeviceId(UUID deviceId);

    @Query(value = "SELECT * FROM USER_DEVICE D WHERE D.DEVICE_ID = :deviceId", nativeQuery = true)
    UserDeviceEntity findByDeviceId(UUID deviceId);

    UserDeviceEntity save(UserDeviceEntity userDeviceEntity);
}
