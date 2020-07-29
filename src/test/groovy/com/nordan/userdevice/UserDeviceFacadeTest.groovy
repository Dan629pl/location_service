package com.nordan.userdevice

import com.nordan.exception.DeviceNotFoundException
import com.nordan.userdevice.model.UserDevice
import spock.lang.Shared
import spock.lang.Specification

class UserDeviceFacadeTest extends Specification {

    def facade = new UserDeviceConfiguration().testUserDeviceFacade()
    @Shared
    def username = "UserName"
    @Shared
    def email = "email@sample.com"
    @Shared
    def manufacturer = "sample manufacturer"
    @Shared
    def model = "Device model"

    def "should save user device to the database"() {

        given: "sample request is prepared"
        def request = UserDevice.builder()
                .username(username)
                .email(email)
                .manufacturer(manufacturer)
                .model(model)
                .build()

        when: "user device is added to the system"
        def result = facade.register(request)

        then: "system returns new user device"
        noExceptionThrown()
        result.deviceId != null
        result.locationId != null
        result.email == email
        result.username == username
        result.manufacturer == manufacturer
        result.model == model
    }

    def "should find user device from the database"() {

        given: "sample request is prepared"
        def request = UserDevice.builder()
                .username(username)
                .email(email)
                .manufacturer(manufacturer)
                .model(model)
                .build()

        when: "user device is added to the system"
        def savedUserDevice = facade.register(request)
        def result = facade.findByDeviceId(savedUserDevice.deviceId)

        then: "system returns saved user device"
        noExceptionThrown()
        result.deviceId == savedUserDevice.deviceId
        result.locationId == savedUserDevice.locationId
        result.email == email
        result.username == username
        result.manufacturer == manufacturer
        result.model == model
    }

    def "should remove user device from the database"() {
        given: "sample request is prepared"
        def request = UserDevice.builder()
                .username(username)
                .email(email)
                .manufacturer(manufacturer)
                .model(model)
                .build()

        when: "user device is added to the system"
        def savedUserDevice = facade.register(request)
        facade.delete(savedUserDevice.deviceId)
        facade.findByDeviceId(savedUserDevice.deviceId)

        then: "system should throw exception"
        thrown(DeviceNotFoundException)
    }


}
