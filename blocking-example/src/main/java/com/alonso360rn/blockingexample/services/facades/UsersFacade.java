package com.alonso360rn.blockingexample.services.facades;

import com.alonso360rn.blockingexample.configuration.EnvironmentConfiguration;
import com.alonso360rn.blockingexample.exceptions.JsonPlaceholderUpstreamException;
import com.alonso360rn.blockingexample.services.facades.models.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class UsersFacade {

    private final RestTemplate restTemplate;

    private final EnvironmentConfiguration environmentConfiguration;

    private static final ParameterizedTypeReference<List<User>> USERS_LIST_RESPONSE_TYPE
            = new ParameterizedTypeReference<>() {};

    public List<User> getUsers() {

        final String url
                = String.format("%s/users", environmentConfiguration.getJsonPlaceholder().getHost());

        try {

             return restTemplate
                     .exchange(url, HttpMethod.GET, null, USERS_LIST_RESPONSE_TYPE)
                     .getBody();
        } catch (Exception e) {

            log.error(String.format("Calling %s with method %s failed.", url, HttpMethod.GET));

            throw new JsonPlaceholderUpstreamException(e);
        }
    }

    public User createUser(User user) {

        final String url
                = String.format("%s/users", environmentConfiguration.getJsonPlaceholder().getHost());

        try {

            HttpEntity<User> request = new HttpEntity<>(user);

            return restTemplate
                    .exchange(url, HttpMethod.POST, request, User.class)
                    .getBody();
        } catch (Exception e) {

            log.error(String.format("Calling %s with method %s failed.", url, HttpMethod.POST));

            throw new JsonPlaceholderUpstreamException(e);
        }
    }
}
