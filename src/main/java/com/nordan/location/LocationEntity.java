package com.nordan.location;

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
@Table(name = "LOCATION")
class LocationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "LOCATION_ID", updatable = false)
    private @NotNull UUID locationId;

    @Column(name = "LATITUDE")
    @With
    private @NotNull Double latitude;

    @Column(name = "LONGITUDE")
    @With
    private @NotNull Double longitude;
}
