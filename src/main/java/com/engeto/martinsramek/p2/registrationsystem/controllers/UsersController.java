package com.engeto.martinsramek.p2.registrationsystem.controllers;

import com.engeto.martinsramek.p2.registrationsystem.domain.dto.UserDto;
import com.engeto.martinsramek.p2.registrationsystem.services.SimplifiedUserService;
import com.engeto.martinsramek.p2.registrationsystem.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/users")
public class UsersController {

    private final UserService userService;
    private final SimplifiedUserService simplifiedUserService;

    public UsersController(final UserService userService, final SimplifiedUserService simplifiedUserService) {
        this.userService = userService;
        this.simplifiedUserService = simplifiedUserService;
    }

    @GetMapping
    public List getUsers(@RequestParam(value = "detail", required=false) String detail) {
        if (detail != null && detail.equals("true")) {
            return userService.findAllUsers();
        } else {
            return simplifiedUserService.findAllSimplifiedUsers();
        }
    }

    @GetMapping("/{ID}")
    public ResponseEntity<UserDto> getUser(@PathVariable("ID") Long id,
                                           @RequestParam(value = "detail") String detail) {
        if (detail.equals("true")) {
            Optional<UserDto> result = userService.findUser(id);
            return result.map(userDto -> new ResponseEntity<>(userDto, HttpStatus.OK))
                    .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
