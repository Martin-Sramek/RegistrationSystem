package com.engeto.martinsramek.p2.registrationsystem.dao;

import com.engeto.martinsramek.p2.registrationsystem.domain.TestData;
import com.engeto.martinsramek.p2.registrationsystem.domain.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class UserDaoTests {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private UserDao userDaoTest;

    private final User testUser1 = TestData.createUser1();

    @Test
    public void createUserCorrectSql() {
        userDaoTest.createUser(testUser1);

        verify(jdbcTemplate).update(
                eq("INSERT INTO users (name, surname, person_id, uuid) VALUES (?, ?, ?, ?)"),
                eq("Petr"),
                eq("Novák"),
                eq("jXa4g3H7oPq2"),
                eq("asdfg456")
        );
    }

    @Test
    public void findUserByIdCorrectSql() {
        userDaoTest.findUserById(testUser1.getId());

        verify(jdbcTemplate).query(
                eq("SELECT * FROM users WHERE id = ?"),
                ArgumentMatchers.<UserDao.UserRowMapper>any(),
                eq(1L)
        );
    }

    @Test
    public void findUserByPersonIdCorrectSql() {
        userDaoTest.findUserByPersonId(testUser1.getPersonId());

        verify(jdbcTemplate).query(
                eq("SELECT * FROM users WHERE person_id = ?"),
                ArgumentMatchers.<UserDao.UserRowMapper>any(),
                eq("jXa4g3H7oPq2")
        );
    }

    @Test
    public void findUserByUuidCorrectSql() {
        userDaoTest.findUserByUuid(testUser1.getUuid());

        verify(jdbcTemplate).query(
                eq("SELECT * FROM users WHERE uuid = ?"),
                ArgumentMatchers.<UserDao.UserRowMapper>any(),
                eq("asdfg456")
        );
    }

    @Test
    public void findAllUsersCorrectSql() {
        userDaoTest.findAllUsers();

        verify(jdbcTemplate).query(
                eq("SELECT * FROM users"),
                ArgumentMatchers.<UserDao.UserRowMapper>any()
        );
    }

    @Test
    public void deleteUserCorrectSql() {
        userDaoTest.deleteUser(testUser1.getId());

        verify(jdbcTemplate).update(
                eq("DELETE FROM users WHERE id = ?"),
                eq(1L)
        );
    }

}
