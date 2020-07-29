package com.nordan.location;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.UUID;

interface LocationRepository extends Repository<LocationEntity, Long> {

    @Query(value = "SELECT * FROM LOCATION D WHERE D.LOCATION_ID = :deviceId", nativeQuery = true)
    LocationEntity findByLocationId(UUID deviceId);

    LocationEntity save(LocationEntity entity);
}