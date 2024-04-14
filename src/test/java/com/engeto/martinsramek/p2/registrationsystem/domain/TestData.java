package com.engeto.martinsramek.p2.registrationsystem.domain;

import com.engeto.martinsramek.p2.registrationsystem.domain.dto.CreateUserDto;
import com.engeto.martinsramek.p2.registrationsystem.domain.dto.SimplifiedUserDto;
import com.engeto.martinsramek.p2.registrationsystem.domain.dto.UserDto;

public class TestData {

    public static User createUser1() {
        return User.builder()
                .id(1L)
                .name("Petr")
                .surname("Novák")
                .personId("jXa4g3H7oPq2")
                .uuid("asdfg456")
                .build();
    }

    public static User createUser2() {
        return User.builder()
                .id(2L)
                .name("Tomáš")
                .surname("Veselý")
                .personId("yB9fR6tK0wLm")
                .uuid("qwert456")
                .build();
    }

    public static User createUser3() {
        return User.builder()
                .id(3L)
                .name("Veronika")
                .surname("Špačková")
                .personId("cN1vZ8pE5sYx")
                .uuid("yxcvb456")
                .build();
    }

    public static SimplifiedUser createSimplifiedUser1() {
        return SimplifiedUser.builder()
                .id(1L)
                .name("Petr")
                .surname("Novák")
                .build();
    }

    public static SimplifiedUser createSimplifiedUser2() {
        return SimplifiedUser.builder()
                .id(2L)
                .name("Tomáš")
                .surname("Veselý")
                .build();
    }

    public static SimplifiedUser createSimplifiedUser3() {
        return SimplifiedUser.builder()
                .id(3L)
                .name("Veronika")
                .surname("Špačková")
                .build();
    }

    public static UserDto createUserDto1() {
        return UserDto.builder()
                .id("1")
                .name("Petr")
                .surname("Novák")
                .personId("jXa4g3H7oPq2")
                .uuid("asdfg456")
                .build();
    }

    public static SimplifiedUserDto createSimplifiedUserDto1() {
        return SimplifiedUserDto.builder()
                .id("1")
                .name("Petr")
                .surname("Novák")
                .build();
    }

    public static SimplifiedUserDto createSimplifiedUserDto2() {
        return SimplifiedUserDto.builder()
                .id("2")
                .name("Tomáš")
                .surname("Veselý")
                .build();
    }

    public static CreateUserDto testDataForUserCreation1() {
        return CreateUserDto.builder()
                .name("Petr")
                .surname("Novák")
                .personId("jXa4g3H7oPq2")
                .build();
    }

}
