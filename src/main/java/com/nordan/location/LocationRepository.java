package com.nordan.location;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.Optional;
import java.util.UUID;

interface LocationRepository extends Repository<LocationEntity, Long> {

    @Query(value = "SELECT * FROM LOCATION D WHERE D.LOCATION_ID = :locationId", nativeQuery = true)
    Optional<LocationEntity> findByLocationId(UUID locationId);

    LocationEntity save(LocationEntity entity);
}