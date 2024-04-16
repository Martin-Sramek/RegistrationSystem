package com.engeto.martinsramek.p2.registrationsystem.controllers;

import com.engeto.martinsramek.p2.registrationsystem.domain.dto.CreateUserDto;
import com.engeto.martinsramek.p2.registrationsystem.domain.dto.SimplifiedUserDto;
import com.engeto.martinsramek.p2.registrationsystem.domain.dto.UserDto;
import com.engeto.martinsramek.p2.registrationsystem.services.SimplifiedUserService;
import com.engeto.martinsramek.p2.registrationsystem.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/user")
public class UserController {

    private final UserService userService;
    private final SimplifiedUserService simplifiedUserService;

    public UserController(final UserService userService, final SimplifiedUserService simplifiedUserService) {
        this.userService = userService;
        this.simplifiedUserService = simplifiedUserService;
    }

    @GetMapping("/{ID}")
    public ResponseEntity<SimplifiedUserDto> getSimplifiedUser(@PathVariable("ID") Long id) {
        Optional<SimplifiedUserDto> result = simplifiedUserService.findSimplifiedUser(id);
        return result.map(simplifiedUserDto -> new ResponseEntity<>(simplifiedUserDto, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping()
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody CreateUserDto createUserDto) {
        if (!userService.personIdValid(createUserDto.getPersonId())) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        } else {
            Optional<UserDto> createdUser = userService.createUser(createUserDto);
            return createdUser.map(newUserDto -> new ResponseEntity<>(newUserDto, HttpStatus.CREATED))
                    .orElse(new ResponseEntity<>(HttpStatus.CONFLICT));
        }
    }

    @DeleteMapping("/{ID}")
    public ResponseEntity deleteUser(@PathVariable("ID") Long id) {
        userService.deleteUser(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PutMapping()
    public ResponseEntity<SimplifiedUserDto> updateUser(@Valid @RequestBody SimplifiedUserDto simplifiedUserDto) {
        Optional<SimplifiedUserDto> updatedUser = simplifiedUserService.update(simplifiedUserDto);
        return updatedUser.map(result -> new ResponseEntity<>(result, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

}
