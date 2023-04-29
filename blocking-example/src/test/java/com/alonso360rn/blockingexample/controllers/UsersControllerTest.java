package com.alonso360rn.blockingexample.controllers;

import com.alonso360rn.blockingexample.BaseTest;
import com.alonso360rn.blockingexample.services.UsersService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(UsersController.class)
class UsersControllerTest extends BaseTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsersService usersService;

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Test
    void shouldGetUsers() throws Exception {

        Mockito.when(usersService.getUsers())
                .thenReturn(getUsersListFromModels());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/users/"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].id").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].name").value("John Wick"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].address").value("suite 1 street 1, city 1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[1].id").value("2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[2].name").value("Obi Wan Kenobi"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[3].address").value("suite 2 street 2, city 2"));

        Mockito.verify(usersService, Mockito.times(1)).getUsers();
    }

    @Test
    void shouldCreateUser() throws Exception {

        Mockito.when(usersService.createUser(Mockito.any()))
                .thenReturn(getUserFromModels());

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/users/")
                .contentType("application/json")
                .content(OBJECT_MAPPER.writeValueAsString(getCreateUserRequest())))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(201))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.id").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.name").value("John Wick"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.address").value("suite 1 street 1, city 1"));
    }
}
