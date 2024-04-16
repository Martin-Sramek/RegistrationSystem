package com.engeto.martinsramek.p2.registrationsystem.controllers;

import com.engeto.martinsramek.p2.registrationsystem.dao.UserDao;
import com.engeto.martinsramek.p2.registrationsystem.domain.TestData;
import com.engeto.martinsramek.p2.registrationsystem.domain.User;
import com.engeto.martinsramek.p2.registrationsystem.domain.dto.CreateUserDto;
import com.engeto.martinsramek.p2.registrationsystem.domain.dto.SimplifiedUserDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class UserControllerIntegrationTests {

    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper;
    private final UserDao userDao;
    private final User testUser1 = TestData.createUser1();
    private final CreateUserDto testCreateUserDto1 = TestData.testDataForUserCreation1();
    SimplifiedUserDto testSimplifiedUserDto2 = TestData.createSimplifiedUserDto2();

    @Autowired
    public UserControllerIntegrationTests(final MockMvc mockMvc,
                                          final ObjectMapper objectMapper,
                                          final UserDao userDao) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
        this.userDao = userDao;
    }

    @Test
    public void createUserHttp201Test() throws Exception {
        String createUserDtoJson = objectMapper.writeValueAsString(testCreateUserDto1);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/v1/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(createUserDtoJson)
        ).andExpect(
                MockMvcResultMatchers.status().isCreated()
        );
    }

    @Test
    public void createUserHttp403Test() throws Exception {
        CreateUserDto wrongPersonId = TestData.testDataForUserCreation1();
        wrongPersonId.setPersonId("invalidcode1");
        String createUserDtoJson = objectMapper.writeValueAsString(wrongPersonId);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/v1/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(createUserDtoJson)
        ).andExpect(
                MockMvcResultMatchers.status().isForbidden()
        );
    }

    @Test
    public void createUserHttp409Test() throws Exception {
        userDao.createUser(testUser1);
        String createUserDtoJson = objectMapper.writeValueAsString(testCreateUserDto1);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/v1/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(createUserDtoJson)
        ).andExpect(
                MockMvcResultMatchers.status().isConflict()
        );
    }

    @Test
    public void createUserReturnsCreatedUserTest() throws Exception {
        String createUserDtoJson = objectMapper.writeValueAsString(testCreateUserDto1);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/v1/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(createUserDtoJson)
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

    @Test
    public void getSimplifiedUserHttp200Test() throws Exception {
        userDao.createUser(testUser1);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/v1/user/1")
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        );
    }

    @Test
    public void getSimplifiedUserHttp404Test() throws Exception {
        userDao.createUser(testUser1);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/v1/user/99")
        ).andExpect(
                MockMvcResultMatchers.status().isNotFound()
        );
    }

    @Test
    public void getSimplifiedUserReturnsSimplifiedUserTest() throws Exception {
        userDao.createUser(testUser1);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/v1/user/1")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.id").value("1")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.name").value("Petr")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.surname").value("Novák")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.personID").doesNotExist()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.uuid").doesNotExist()
        );
    }

    @Test
    public void deleteUserHttp204Test() throws Exception {
        userDao.createUser(testUser1);

        mockMvc.perform(
                MockMvcRequestBuilders.delete("/api/v1/user/1")
        ).andExpect(
                MockMvcResultMatchers.status().isNoContent()
        );
    }

    @Test
    public void updateUserHttp200Test() throws Exception {
        userDao.createUser(testUser1);
        testSimplifiedUserDto2.setId(Long.toString(testUser1.getId()));
        String simplifiedUserDtoJson = objectMapper.writeValueAsString(testSimplifiedUserDto2);

        mockMvc.perform(
                MockMvcRequestBuilders.put("/api/v1/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(simplifiedUserDtoJson)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        );
    }

    @Test
    public void updateUserHttp404Test() throws Exception {
        userDao.createUser(testUser1);
        testSimplifiedUserDto2.setId("99");
        String simplifiedUserDtoJson = objectMapper.writeValueAsString(testSimplifiedUserDto2);

        mockMvc.perform(
                MockMvcRequestBuilders.put("/api/v1/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(simplifiedUserDtoJson)
        ).andExpect(
                MockMvcResultMatchers.status().isNotFound()
        );
    }

    @Test
    public void updateUserReturnsSimplifiedUserTest() throws Exception {
        userDao.createUser(testUser1);
        testSimplifiedUserDto2.setId(Long.toString(testUser1.getId()));
        String simplifiedUserDtoJson = objectMapper.writeValueAsString(testSimplifiedUserDto2);

        mockMvc.perform(
                MockMvcRequestBuilders.put("/api/v1/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(simplifiedUserDtoJson)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.id").value("1")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.name").value("Tomáš")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.surname").value("Veselý")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.personID").doesNotExist()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.uuid").doesNotExist()
        );
    }

}
