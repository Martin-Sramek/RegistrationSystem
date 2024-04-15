package com.engeto.martinsramek.p2.registrationsystem.dao;

import com.engeto.martinsramek.p2.registrationsystem.domain.SimplifiedUser;
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
public class SimplifiedUserDaoIntegrationTests {

    private final SimplifiedUserDao simplifiedUserDaoTest;
    private final UserDao userDaoTest;
    private final User testUser1 = TestData.createUser1();
    private final User testUser2 = TestData.createUser2();
    private final User testUser3 = TestData.createUser3();
    private final SimplifiedUser simplifiedTestUser1 = TestData.createSimplifiedUser1();
    private final SimplifiedUser simplifiedTestUser2 = TestData.createSimplifiedUser2();
    private final SimplifiedUser simplifiedTestUser3 = TestData.createSimplifiedUser3();

    @Autowired
    public SimplifiedUserDaoIntegrationTests(final SimplifiedUserDao simplifiedUserDaoTest,
                                             final UserDao userDaoTest) {
        this.simplifiedUserDaoTest = simplifiedUserDaoTest;
        this.userDaoTest = userDaoTest;
    }

    @Test
    public void findSimplifiedUserByIdTest() {
        userDaoTest.createUser(testUser1);
        Optional<SimplifiedUser> result = simplifiedUserDaoTest.findSimplifiedUserById(testUser1.getId());

        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(simplifiedTestUser1);
    }

    @Test
    public void findAllSimplifiedUsersTest() {
        userDaoTest.createUser(testUser1);
        userDaoTest.createUser(testUser2);
        userDaoTest.createUser(testUser3);
        List<SimplifiedUser> allSimplifiedUsers = simplifiedUserDaoTest.findAllSimplifiedUsers();

        assertThat(allSimplifiedUsers).hasSize(3)
                .containsExactly(simplifiedTestUser1, simplifiedTestUser2, simplifiedTestUser3);
    }

    @Test
    public void updateUserTest() {
        userDaoTest.createUser(testUser1);

        SimplifiedUser update = SimplifiedUser
                .builder()
                .id(testUser1.getId())
                .name("Jarmila")
                .surname("Černá")
                .build();

        simplifiedUserDaoTest.updateUser(update);
        Optional<SimplifiedUser> updatedUser = simplifiedUserDaoTest.findSimplifiedUserById(testUser1.getId());

        assertThat(updatedUser).isPresent();
        assertThat(updatedUser.get()).isEqualTo(update);
    }

}
