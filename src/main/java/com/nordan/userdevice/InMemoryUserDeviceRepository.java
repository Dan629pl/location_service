package com.nordan.userdevice;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryUserDeviceRepository implements UserDeviceRepository {

    private final Map<UUID, UserDeviceEntity> db = new ConcurrentHashMap<>();

    @Override
    public void deleteByDeviceId(UUID deviceId) {
        db.values().removeIf(entity -> deviceId.equals(entity.getDeviceId()));
    }

    @Override
    public Optional<UserDeviceEntity> findByDeviceId(UUID deviceId) {
        return Optional.ofNullable(db.get(deviceId));
    }

    @Override
    public UserDeviceEntity save(UserDeviceEntity userDeviceEntity) {
        db.put(userDeviceEntity.getDeviceId(), userDeviceEntity);
        return db.get(userDeviceEntity.getDeviceId());
    }
}
