package com.engeto.martinsramek.p2.registrationsystem.services;

import com.engeto.martinsramek.p2.registrationsystem.dao.SimplifiedUserDao;
import com.engeto.martinsramek.p2.registrationsystem.domain.SimplifiedUser;
import com.engeto.martinsramek.p2.registrationsystem.domain.dto.SimplifiedUserDto;
import com.engeto.martinsramek.p2.registrationsystem.mapper.Mapper;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class SimplifiedUserService {

    private final SimplifiedUserDao simplifiedUserDao;

    public SimplifiedUserService(final SimplifiedUserDao simplifiedUserDao) {
        this.simplifiedUserDao = simplifiedUserDao;
    }

    public Optional<SimplifiedUserDto> findSimplifiedUser(Long id) {
        Optional<SimplifiedUser> simplifiedUser = simplifiedUserDao.findSimplifiedUserById(id);
        return simplifiedUser.map(foundSimplifiedUser -> {
            SimplifiedUserDto simplifiedUserDto = Mapper.fromSimplifiedUserToSimplifiedUserDto(foundSimplifiedUser);
            return Optional.of(simplifiedUserDto);
        }).orElse(Optional.empty());
    }

    public List<SimplifiedUserDto> findAllSimplifiedUsers() {
        List<SimplifiedUser> allSimplifiedUsers = simplifiedUserDao.findAllSimplifiedUsers();
        return Mapper.fromListSimplifiedUserToListSimplifiedUserDto(allSimplifiedUsers);
    }

    public Optional<SimplifiedUserDto> update(SimplifiedUserDto simplifiedUserDto) {
        SimplifiedUser simplifiedUser = Mapper.fromSimplifiedUserDtoToSimplifiedUser(simplifiedUserDto);
        Optional<SimplifiedUser> foundUser = simplifiedUserDao.findSimplifiedUserById(simplifiedUser.getId());

        if (foundUser.isPresent()) {
            SimplifiedUser userToUpdate = foundUser.get();
            Optional.ofNullable(simplifiedUserDto.getName()).ifPresent(userToUpdate::setName);
            Optional.ofNullable(simplifiedUserDto.getSurname()).ifPresent(userToUpdate::setSurname);
            SimplifiedUser savedSimplifiedUser = simplifiedUserDao.updateUser(userToUpdate).get();
            return Optional.of(Mapper.fromSimplifiedUserToSimplifiedUserDto(savedSimplifiedUser));
        } else {
            return Optional.empty();
        }
    }

}
