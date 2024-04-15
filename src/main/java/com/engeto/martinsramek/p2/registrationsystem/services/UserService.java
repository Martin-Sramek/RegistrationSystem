package com.engeto.martinsramek.p2.registrationsystem.services;

import com.engeto.martinsramek.p2.registrationsystem.dao.UserDao;
import com.engeto.martinsramek.p2.registrationsystem.domain.User;
import com.engeto.martinsramek.p2.registrationsystem.domain.dto.CreateUserDto;
import com.engeto.martinsramek.p2.registrationsystem.domain.dto.UserDto;
import com.engeto.martinsramek.p2.registrationsystem.mapper.Mapper;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

@Service
public class UserService {

    private final UserDao userDao;
    private final Set<String> personIds = new HashSet<>();

    public UserService(final UserDao userDao) {
        this.userDao = userDao;
    }

    @PostConstruct
    private void loadPersonIds() throws IOException {
        try (InputStream inputStream = getClass().getResourceAsStream("/dataPersonId.txt");
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                personIds.add(line);
            }
        }
    }

    public Boolean personIdValid(String personId) {
        return personIds.contains(personId);
    }

    public Optional<UserDto> createUser(CreateUserDto createUserDto) {
        Optional<User> foundUser = userDao.findUserByPersonId(createUserDto.getPersonId());
        if (foundUser.isPresent()) {
            return Optional.empty();
        } else {
            Optional<User> createdUser = userDao.createUser(User
                    .builder()
                    .id(null)
                    .name(createUserDto.getName())
                    .surname(createUserDto.getSurname())
                    .personId(createUserDto.getPersonId())
                    .uuid(UUID.randomUUID().toString())
                    .build());
            return Optional.of(createdUser.map(Mapper::fromUserToUserDto)
                    .orElse(UserDto.builder().id(null).name(null).surname(null).personId(null).uuid(null).build()));
        }
    }

    public void deleteUser(Long id) {
        userDao.deleteUser(id);
    }

    public List<UserDto> findAllUsers() {
        List<User> allUsers = userDao.findAllUsers();
        return Mapper.fromListUserToListUserDto(allUsers);
    }

    public Optional<UserDto> findUser(Long id) {
        Optional<User> user = userDao.findUserById(id);
        return user.map(foundUser -> {
            UserDto userDto = Mapper.fromUserToUserDto(foundUser);
            return Optional.of(userDto);
        }).orElse(Optional.empty());
    }

}
