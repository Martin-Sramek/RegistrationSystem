package com.engeto.martinsramek.p2.registrationsystem.mapper;

import com.engeto.martinsramek.p2.registrationsystem.domain.SimplifiedUser;
import com.engeto.martinsramek.p2.registrationsystem.domain.User;
import com.engeto.martinsramek.p2.registrationsystem.domain.dto.SimplifiedUserDto;
import com.engeto.martinsramek.p2.registrationsystem.domain.dto.UserDto;
import java.util.List;

public class Mapper {

    public static User fromUserDtoToUser(UserDto userDto) {
        return User
                .builder()
                .id(Long.parseLong(userDto.getId()))
                .name(userDto.getName())
                .surname(userDto.getSurname())
                .personId(userDto.getPersonId())
                .uuid(userDto.getUuid())
                .build();
    }

    public static UserDto fromUserToUserDto(User user) {
                return UserDto
                .builder()
                .id(Long.toString(user.getId()))
                .name(user.getName())
                .surname(user.getSurname())
                .personId(user.getPersonId())
                .uuid(user.getUuid())
                .build();
    }

    public static SimplifiedUser fromSimplifiedUserDtoToSimplifiedUser(SimplifiedUserDto simplifiedUserDto) {
        return SimplifiedUser
                .builder()
                .id(Long.parseLong(simplifiedUserDto.getId()))
                .name(simplifiedUserDto.getName())
                .surname(simplifiedUserDto.getSurname())
                .build();
    }

    public static SimplifiedUserDto fromSimplifiedUserToSimplifiedUserDto(SimplifiedUser simplifiedUser) {
        return SimplifiedUserDto
                .builder()
                .id(Long.toString(simplifiedUser.getId()))
                .name(simplifiedUser.getName())
                .surname(simplifiedUser.getSurname())
                .build();
    }

    public static List<UserDto> fromListUserToListUserDto(List<User> users) {
        return users.stream().map(Mapper::fromUserToUserDto).toList();
    }

    public static List<User> fromListUserDtoToListUser(List<UserDto> usersDto) {
        return usersDto.stream().map(Mapper::fromUserDtoToUser).toList();
    }

    public static List<SimplifiedUserDto> fromListSimplifiedUserToListSimplifiedUserDto(List<SimplifiedUser> simplifiedUsers) {
        return simplifiedUsers.stream().map(Mapper::fromSimplifiedUserToSimplifiedUserDto).toList();
    }

    public static List<SimplifiedUser> fromListSimplifiedUserDtoToListSimplifiedUser(List<SimplifiedUserDto> simplifiedUsersDto) {
        return simplifiedUsersDto.stream().map(Mapper::fromSimplifiedUserDtoToSimplifiedUser).toList();
    }

}
