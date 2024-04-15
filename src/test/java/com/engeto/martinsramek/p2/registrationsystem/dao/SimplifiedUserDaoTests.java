package com.engeto.martinsramek.p2.registrationsystem.dao;

import com.engeto.martinsramek.p2.registrationsystem.domain.SimplifiedUser;
import com.engeto.martinsramek.p2.registrationsystem.domain.TestData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;
import org.mockito.ArgumentMatchers;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class SimplifiedUserDaoTests {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private SimplifiedUserDao simplifiedUserDaoTest;

    private final SimplifiedUser simplifiedTestUser1 = TestData.createSimplifiedUser1();

    @Test
    public void findSimplifiedUserByIdCorrectSql() {
        simplifiedUserDaoTest.findSimplifiedUserById(simplifiedTestUser1.getId());

        verify(jdbcTemplate).query(
                eq("SELECT id, name, surname FROM users WHERE id = ?"),
                ArgumentMatchers.<SimplifiedUserDao.SimplifiedUserRowMapper>any(),
                eq(1L)
        );
    }

    @Test
    public void findAllSimplifiedUsersCorrectSql() {
        simplifiedUserDaoTest.findAllSimplifiedUsers();

        verify(jdbcTemplate).query(
                eq("SELECT id, name, surname FROM users"),
                ArgumentMatchers.<SimplifiedUserDao.SimplifiedUserRowMapper>any()
        );
    }

    @Test
    public void updateUserCorrectSql() {
        simplifiedUserDaoTest.updateUser(simplifiedTestUser1);

        verify(jdbcTemplate).update(
                eq("UPDATE users SET name = ?, surname = ? WHERE id = ?"),
                eq("Petr"),
                eq("Novák"),
                eq(1L)
        );
    }

}
