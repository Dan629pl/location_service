package com.nordan.location;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

class InMemoryLocationRepository implements LocationRepository {
    private final Map<UUID, LocationEntity> db = new ConcurrentHashMap<>();

    @Override
    public Optional<LocationEntity> findByLocationId(UUID locationId) {
        return Optional.ofNullable(db.get(locationId));
    }

    @Override
    public LocationEntity save(LocationEntity entity) {
        db.put(entity.getLocationId(), entity);
        return db.get(entity.getLocationId());
    }

    @Override
    public void deleteByLocationId(UUID locationId) {
        db.values().removeIf(entity -> locationId.equals(entity.getLocationId()));
    }
}
