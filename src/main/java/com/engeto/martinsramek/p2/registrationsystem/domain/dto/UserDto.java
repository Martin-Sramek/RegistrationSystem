package com.engeto.martinsramek.p2.registrationsystem.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private String id;

    private String name;

    private String surname;

    @JsonProperty("personID")
    private String personId;

    private String uuid;

}
