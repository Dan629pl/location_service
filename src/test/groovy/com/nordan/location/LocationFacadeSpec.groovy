package com.nordan.location

import com.nordan.exception.LocationNotFoundException
import com.nordan.location.model.Location
import com.nordan.userdevice.UserDeviceFacade
import com.nordan.userdevice.model.UserDevice
import spock.lang.Shared
import spock.lang.Specification

class LocationFacadeSpec extends Specification {

    def userDeviceFacade = Mock(UserDeviceFacade)
    def facade = new LocationConfiguration(userDeviceFacade).testLocationFacade()
    @Shared
    def deviceId = UUID.randomUUID()
    @Shared
    def locationId = UUID.randomUUID()
    @Shared
    def latitude = 1337.0d
    @Shared
    def longitude = 999.5d
    @Shared
    def userDevice = createUserDevice()

    def "should not found location from the database"() {

        given: "mock user device find facade"
        userDeviceFacade.findByDeviceId(deviceId) >> userDevice

        when: "location is not in database"
        facade.findByDeviceId(deviceId)

        then: "system should throw exception"
        thrown(LocationNotFoundException)
    }

    def "should update location in database"() {

        given: "mock user device find facade and prepare request"
        userDeviceFacade.findByDeviceId(deviceId) >> userDevice
        def request = Location.builder()
                .latitude(latitude)
                .longitude(longitude)
                .build()

        when: "update location in database"
        def result = facade.update(deviceId, request)

        then: "system return updated location"
        noExceptionThrown()
        latitude == result.latitude
        longitude == result.longitude
    }

    def "should find location in database"() {

        given: "mock user device find facade and prepare request"
        userDeviceFacade.findByDeviceId(deviceId) >> userDevice
        def request = Location.builder()
                .latitude(latitude)
                .longitude(longitude)
                .build()

        when: "update location in database"
        facade.update(deviceId, request)
        def result = facade.findByDeviceId(deviceId)

        then: "system return updated location"
        noExceptionThrown()
        latitude == result.latitude
        longitude == result.longitude
    }

    UserDevice createUserDevice() {
        return UserDevice.builder()
                .model("Sample model")
                .manufacturer("Sample manufacturer")
                .username("Sample username")
                .email("sample@email.com")
                .locationId(locationId)
                .deviceId(deviceId)
                .build()
    }
}
