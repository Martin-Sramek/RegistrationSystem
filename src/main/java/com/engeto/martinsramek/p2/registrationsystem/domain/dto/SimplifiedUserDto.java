package com.engeto.martinsramek.p2.registrationsystem.domain.dto;

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
public class SimplifiedUserDto {

    @NotEmpty
    @NotBlank
    private String id;

    @Size(max = 255)
    private String name;

    @Size(max = 255)
    private String surname;

}
