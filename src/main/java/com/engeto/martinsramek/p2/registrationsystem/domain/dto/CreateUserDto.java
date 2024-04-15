package com.engeto.martinsramek.p2.registrationsystem.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserDto {

    @NotEmpty
    @NotBlank
    @Size(max = 255)
    private String name;

    @Size(max = 255)
    private String surname;

    @NotEmpty
    @NotBlank
    @Size(min = 12, max = 12)
    @JsonProperty("personID")
    private String personId;

}
