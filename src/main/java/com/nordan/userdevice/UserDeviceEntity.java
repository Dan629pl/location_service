package com.nordan.userdevice;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor
@Entity
@EqualsAndHashCode(exclude = "id")
@Table(name = "USER_DEVICE")
class UserDeviceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "DEVICE_ID", updatable = false)
    private @NotNull UUID deviceId;

    @Column(name = "LOCATION_ID", updatable = false)
    @With
    private @NotNull UUID locationId;

    @Column(name = "USERNAME")
    @With
    private @NotNull String username;

    @Column(name = "E_MAIL")
    @With
    private @NotNull String email;

    @Column(name = "MANUFACTURER")
    @With
    private @NotNull String manufacturer;

    @Column(name = "MODEL")
    @With
    private @NotNull String model;
}