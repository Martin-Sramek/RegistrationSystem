package com.engeto.martinsramek.p2.registrationsystem.dao;

import com.engeto.martinsramek.p2.registrationsystem.domain.TestData;
import com.engeto.martinsramek.p2.registrationsystem.domain.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class UserDaoIntegrationTests {

    private final UserDao userDaoTest;
    private final User testUser1 = TestData.createUser1();
    private final User testUser2 = TestData.createUser2();
    private final User testUser3 = TestData.createUser3();


    @Autowired
    public UserDaoIntegrationTests(final UserDao userDaoTest) {
        this.userDaoTest = userDaoTest;
    }

    @Test
    public void createUserTest() {
        Optional<User> newUser = userDaoTest.createUser(testUser1);
        Optional<User> foundUser = userDaoTest.findUserById(testUser1.getId());

        assertThat(newUser).isPresent();
        assertThat(foundUser).isPresent();
        assertThat(newUser.get()).isEqualTo(testUser1);
        assertThat(foundUser.get()).isEqualTo(testUser1);
    }

    @Test
    public void findUserByIdTest() {
        userDaoTest.createUser(testUser1);
        Optional<User> result = userDaoTest.findUserById(testUser1.getId());

        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(testUser1);
    }

    @Test
    public void findUserByPersonIdTest() {
        userDaoTest.createUser(testUser1);
        Optional<User> result = userDaoTest.findUserByPersonId(testUser1.getPersonId());

        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(testUser1);
    }

    @Test
    public void findUserByUuidTest() {
        userDaoTest.createUser(testUser1);
        Optional<User> result = userDaoTest.findUserByUuid(testUser1.getUuid());

        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(testUser1);
    }

    @Test
    public void findAllUsersTest() {
        userDaoTest.createUser(testUser1);
        userDaoTest.createUser(testUser2);
        userDaoTest.createUser(testUser3);
        List<User> allUsers = userDaoTest.findAllUsers();

        assertThat(allUsers).hasSize(3)
                .containsExactly(testUser1, testUser2, testUser3);
    }

    @Test
    public void deleteUserTest() {
        userDaoTest.createUser(testUser1);
        Optional<User> result = userDaoTest.findUserById(testUser1.getId());
        userDaoTest.deleteUser(testUser1.getId());
        Optional<User> resultAfterDeletion = userDaoTest.findUserById(testUser1.getId());
        List<User> allUsers = userDaoTest.findAllUsers();

        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(testUser1);
        assertThat(resultAfterDeletion).isNotPresent();
        assertThat(allUsers).isEmpty();
    }

}
