package com.alonso360rn.services.facades;

import com.alonso360rn.blockingexample.configuration.EnvironmentConfiguration;
import com.alonso360rn.blockingexample.services.facades.UsersFacade;
import com.alonso360rn.blockingexample.services.facades.models.User;
import com.alonso360rn.services.BaseTest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Answers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.client.match.MockRestRequestMatchers;
import org.springframework.test.web.client.response.MockRestResponseCreators;

import java.util.List;

@RestClientTest(value = UsersFacade.class)
@AutoConfigureWebClient(registerRestTemplate = true)
class UsersFacadeTest extends BaseTest {

    @Autowired
    private MockRestServiceServer mockRestServiceServer;

    @MockBean(answer = Answers.RETURNS_DEEP_STUBS)
    private EnvironmentConfiguration environmentConfiguration;

    @Autowired
    private UsersFacade usersFacade;

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @AfterEach
    void resetMockServer() {
        mockRestServiceServer.reset();
    }

    @Test
    void shouldGetUsers() throws JsonProcessingException {

        Mockito.when(environmentConfiguration.getJsonPlaceholder().getHost())
                .thenReturn("json-placeholder-host");

        mockRestServiceServer
                .expect(MockRestRequestMatchers.requestTo("/json-placeholder-host/users"))
                .andExpect(MockRestRequestMatchers.method(HttpMethod.GET))
                .andRespond(MockRestResponseCreators.withSuccess(OBJECT_MAPPER.writeValueAsString(getUsersList()), MediaType.APPLICATION_JSON));

        final List<User> response = usersFacade.getUsers();

        Assertions.assertEquals(2, response.size());
        Assertions.assertEquals("1", response.get(0).getId());
        Assertions.assertEquals("name 1", response.get(0).getName());
        Assertions.assertEquals("city 1", response.get(0).getAddress().getCity());
        Assertions.assertEquals("street 1", response.get(0).getAddress().getStreet());
        Assertions.assertEquals("suite 1", response.get(0).getAddress().getSuite());
        Assertions.assertEquals("2", response.get(1).getId());
        Assertions.assertEquals("name 2", response.get(1).getName());
        Assertions.assertEquals("city 2", response.get(1).getAddress().getCity());
        Assertions.assertEquals("street 2", response.get(1).getAddress().getStreet());
        Assertions.assertEquals("suite 2", response.get(1).getAddress().getSuite());
    }

    @Test
    void shouldCreateUser() throws JsonProcessingException {

        final User user = getUser("1");

        Mockito.when(environmentConfiguration.getJsonPlaceholder().getHost())
                .thenReturn("json-placeholder-host");

        mockRestServiceServer
                .expect(MockRestRequestMatchers.requestTo("/json-placeholder-host/users"))
                .andExpect(MockRestRequestMatchers.method(HttpMethod.POST))
                .andRespond(MockRestResponseCreators.withSuccess(OBJECT_MAPPER.writeValueAsString(user), MediaType.APPLICATION_JSON));

        final User response = usersFacade.createUser(user);

        Assertions.assertEquals("1", response.getId());
        Assertions.assertEquals("John Wick", response.getName());
        Assertions.assertEquals("city", response.getAddress().getCity());
        Assertions.assertEquals("street", response.getAddress().getStreet());
        Assertions.assertEquals("suite", response.getAddress().getSuite());
    }
}
