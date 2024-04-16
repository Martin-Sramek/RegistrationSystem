package com.engeto.martinsramek.p2.registrationsystem.controllers;

import com.engeto.martinsramek.p2.registrationsystem.dao.UserDao;
import com.engeto.martinsramek.p2.registrationsystem.domain.TestData;
import com.engeto.martinsramek.p2.registrationsystem.domain.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class UsersControllerIntegrationTests {

    private final MockMvc mockMvc;
    private final UserDao userDao;
    private final User testUser1 = TestData.createUser1();
    private final User testUser2 = TestData.createUser2();

    @Autowired
    public UsersControllerIntegrationTests(final MockMvc mockMvc, final UserDao userDao) {
        this.mockMvc = mockMvc;
        this.userDao = userDao;
    }

    @Test
    public void getUsersHttp200Test() throws Exception {
        userDao.createUser(testUser1);
        userDao.createUser(testUser2);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/v1/users")
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        );
    }

    @Test
    public void getUsersDetailHttp200Test() throws Exception {
        userDao.createUser(testUser1);
        userDao.createUser(testUser2);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/v1/users?detail=true")
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        );
    }

    @Test
    public void getUsersEmptyHttp200Test() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/v1/users?detail=true")
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        );
    }

    @Test
    public void getUsersReturnsSimplifiedListTest() throws Exception {
        userDao.createUser(testUser1);
        userDao.createUser(testUser2);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/v1/users")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].id").value("1")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].name").value("Petr")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].surname").value("Novák")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].personID").doesNotExist()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].uuid").doesNotExist()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[1].id").value("2")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[1].name").value("Tomáš")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[1].surname").value("Veselý")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[1].personID").doesNotExist()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[1].uuid").doesNotExist()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[2]").doesNotExist()
        );
    }

    @Test
    public void getUsersReturnsListTest() throws Exception {
        userDao.createUser(testUser1);
        userDao.createUser(testUser2);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/v1/users?detail=true")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].id").value("1")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].name").value("Petr")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].surname").value("Novák")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].personID").value("jXa4g3H7oPq2")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].uuid").isString()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[1].id").value("2")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[1].name").value("Tomáš")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[1].surname").value("Veselý")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[1].personID").value("yB9fR6tK0wLm")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[1].uuid").isString()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[2]").doesNotExist()
        );
    }

    @Test
    public void getUserHttp200Test() throws Exception {
        userDao.createUser(testUser1);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/v1/users/1?detail=true")
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        );
    }

    @Test
    public void getUserHttp404Test() throws Exception {
        userDao.createUser(testUser1);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/v1/users/99?detail=true")
        ).andExpect(
                MockMvcResultMatchers.status().isNotFound()
        );
    }

    @Test
    public void getUserHttp400Test() throws Exception {
        userDao.createUser(testUser1);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/v1/users/1")
        ).andExpect(
                MockMvcResultMatchers.status().isBadRequest()
        );
    }

    @Test
    public void getUserReturnsUserTest() throws Exception {
        userDao.createUser(testUser1);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/v1/users/1?detail=true")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.id").value("1")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.name").value("Petr")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.surname").value("Novák")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.personID").value("jXa4g3H7oPq2")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.uuid").isString()
        );
    }

}
