package com.alonso360rn.services;

import com.alonso360rn.blockingexample.models.User;
import com.alonso360rn.blockingexample.services.UsersService;
import com.alonso360rn.blockingexample.services.facades.UsersFacade;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class UsersServiceTest extends BaseTest {

    @Mock
    private UsersFacade usersFacade;

    @InjectMocks
    private UsersService usersService;

    @Test
    void shouldGetUsers() {

        Mockito.when(usersFacade.getUsers()).thenReturn(getUsersList());

        final List<User> usersList = usersService.getUsers();

        Assertions.assertEquals(2, usersList.size());
        Assertions.assertEquals("1", usersList.get(0).getId());
        Assertions.assertEquals("name 1", usersList.get(0).getName());
        Assertions.assertEquals("suite 1 street 1, city 1", usersList.get(0).getAddress());
        Assertions.assertEquals("2", usersList.get(1).getId());
        Assertions.assertEquals("name 2", usersList.get(1).getName());
        Assertions.assertEquals("suite 2 street 2, city 2", usersList.get(1).getAddress());

        Mockito.verify(usersFacade, Mockito.times(1)).getUsers();
    }

    @Test
    void shouldCreateUsers() {

        final com.alonso360rn.blockingexample.services.facades.models.User userFromFacade = getUser("1");

        Mockito.when(usersFacade.createUser(ArgumentMatchers.any())).thenReturn(userFromFacade);

        final User user = usersService.createUser(getCreateUserRequest());

        Assertions.assertEquals("1", user.getId());
        Assertions.assertEquals("John Wick", user.getName());
        Assertions.assertEquals("suite street, city", user.getAddress());

        ArgumentCaptor<com.alonso360rn.blockingexample.services.facades.models.User> userFromFacadeCaptor
                = ArgumentCaptor.forClass(com.alonso360rn.blockingexample.services.facades.models.User.class);

        Mockito.verify(usersFacade, Mockito.times(1))
                .createUser(userFromFacadeCaptor.capture());

        Assertions.assertEquals("John Wick", userFromFacadeCaptor.getValue().getName());
    }
}
