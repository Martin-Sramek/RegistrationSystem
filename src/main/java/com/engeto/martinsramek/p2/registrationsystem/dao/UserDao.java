package com.engeto.martinsramek.p2.registrationsystem.dao;

import com.engeto.martinsramek.p2.registrationsystem.domain.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Component
public class UserDao {

    private final JdbcTemplate jdbcTemplate;
    private final UserRowMapper userRowMapper = new UserRowMapper();

    public UserDao(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public static class UserRowMapper implements RowMapper<User> {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            return User
                    .builder()
                    .id(rs.getLong("id"))
                    .name(rs.getString("name"))
                    .surname(rs.getString("surname"))
                    .personId(rs.getString("person_id"))
                    .uuid(rs.getString("uuid"))
                    .build();
        }
    }

    public Optional<User> createUser(User user) {
        jdbcTemplate.update(
                "INSERT INTO users (name, surname, person_id, uuid) VALUES (?, ?, ?, ?)",
                user.getName(), user.getSurname(), user.getPersonId(), user.getUuid()
        );
        return findUserByUuid(user.getUuid());
    }

    public Optional<User> findUserById(Long id) {
        List<User> result = jdbcTemplate.query(
                "SELECT * FROM users WHERE id = ?",
                userRowMapper, id
        );
        return result.stream().findFirst();
    }

    public Optional<User> findUserByPersonId(String personId) {
        List<User> result = jdbcTemplate.query(
                "SELECT * FROM users WHERE person_id = ?",
                userRowMapper, personId
        );
        return result.stream().findFirst();
    }

    public Optional<User> findUserByUuid(String uuid) {
        List<User> result = jdbcTemplate.query(
                "SELECT * FROM users WHERE uuid = ?",
                userRowMapper, uuid
        );
        return result.stream().findFirst();
    }

    public List<User> findAllUsers() {
        return jdbcTemplate.query(
                "SELECT * FROM users",
                userRowMapper
        );
    }

    public void deleteUser(Long id) {
        jdbcTemplate.update(
                "DELETE FROM users WHERE id = ?",
                id
        );
    }

}
