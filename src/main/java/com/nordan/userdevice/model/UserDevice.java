package com.nordan.userdevice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;
import java.util.UUID;

@Getter
@Builder
@EqualsAndHashCode
@AllArgsConstructor
@Validated
@JsonIgnoreProperties(value = {"locationId"})
public class UserDevice {

    private final UUID deviceId;
    private final UUID locationId;
    @NonNull
    private final String username;
    @NonNull
    @Email
    private final String email;
    private final String manufacturer;
    private final String model;
}
