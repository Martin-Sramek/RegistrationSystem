package com.engeto.martinsramek.p2.registrationsystem.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SimplifiedUserDto {

    private String id;

    private String name;

    private String surname;

}
