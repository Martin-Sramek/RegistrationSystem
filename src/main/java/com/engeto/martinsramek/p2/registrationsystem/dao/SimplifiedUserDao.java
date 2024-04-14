package com.engeto.martinsramek.p2.registrationsystem.dao;

import com.engeto.martinsramek.p2.registrationsystem.domain.SimplifiedUser;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Component
public class SimplifiedUserDao {

    private final JdbcTemplate jdbcTemplate;
    private final SimplifiedUserRowMapper simplifiedUserRowMapper = new SimplifiedUserRowMapper();

    public SimplifiedUserDao(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public static class SimplifiedUserRowMapper implements RowMapper<SimplifiedUser> {
        @Override
        public SimplifiedUser mapRow(ResultSet rs, int rowNum) throws SQLException {
            return SimplifiedUser
                    .builder()
                    .id(rs.getLong("id"))
                    .name(rs.getString("name"))
                    .surname(rs.getString("surname"))
                    .build();
        }
    }

    public Optional<SimplifiedUser> findSimplifiedUserById(Long id) {
        List<SimplifiedUser> result = jdbcTemplate.query(
                "SELECT id, name, surname FROM users WHERE id = ?",
                simplifiedUserRowMapper, id
        );
        return result.stream().findFirst();
    }

    public List<SimplifiedUser> findAllSimplifiedUsers() {
        return jdbcTemplate.query(
                "SELECT id, name, surname FROM users",
                simplifiedUserRowMapper
        );
    }

    public Optional<SimplifiedUser> updateUser(SimplifiedUser simplifiedUser) {
        jdbcTemplate.update(
                "UPDATE users SET name = ?, surname = ? WHERE id = ?",
                simplifiedUser.getName(), simplifiedUser.getSurname(), simplifiedUser.getId()
        );
        return findSimplifiedUserById(simplifiedUser.getId());
    }

}
