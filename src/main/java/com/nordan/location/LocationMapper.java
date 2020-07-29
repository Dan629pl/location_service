package com.nordan.location;

import com.nordan.location.model.Location;

class LocationMapper {

     Location map(LocationEntity entity) {
         return Location.builder()
                 .latitude(entity.getLatitude())
                 .longitude(entity.getLongitude())
                 .build();
     }
}
