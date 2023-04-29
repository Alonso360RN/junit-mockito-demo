package com.alonso360rn.blockingexample.services;

import com.alonso360rn.blockingexample.models.*;
import com.alonso360rn.blockingexample.services.facades.UsersFacade;
import com.alonso360rn.blockingexample.services.facades.models.Address;
import com.alonso360rn.blockingexample.services.facades.models.Company;
import com.alonso360rn.blockingexample.services.facades.models.Geo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UsersService {

    private final UsersFacade usersFacade;

    public List<User> getUsers() {

        List<com.alonso360rn.blockingexample.services.facades.models.User> usersListFromFacade
                = usersFacade.getUsers();

        return usersListFromFacade
                .stream()
                .map(userFromFacade -> User.builder()
                        .address(getFormattedAddress(userFromFacade.getAddress()))
                        .id(userFromFacade.getId())
                        .name(userFromFacade.getName())
                        .build()
                )
                .toList();
    }

    public User createUser(CreateUserRequest createUserRequest) {

        final CreateUserAddress createUserAddress = createUserRequest.getAddress();
        final CreateUserGeo createUserGeo = createUserAddress.getGeo();
        final CreateUserCompany createUserCompany = createUserRequest.getCompany();

        final Geo geo = Geo.builder()
                .lat(createUserGeo.getLat())
                .lng(createUserGeo.getLng())
                .build();

        final Address address = Address.builder()
                .city(createUserAddress.getCity())
                .geo(geo)
                .street(createUserAddress.getStreet())
                .suite(createUserAddress.getSuite())
                .zipCode(createUserAddress.getZipCode())
                .build();

        final Company company = Company.builder()
                .bs(createUserCompany.getBs())
                .catchPhrase(createUserCompany.getCatchPhrase())
                .name(createUserCompany.getName())
                .build();

        final com.alonso360rn.blockingexample.services.facades.models.User user
                = com.alonso360rn.blockingexample.services.facades.models.User.builder()
                .address(address)
                .company(company)
                .email(createUserRequest.getEmail())
                .name(createUserRequest.getName())
                .phone(createUserRequest.getPhone())
                .username(createUserRequest.getUsername())
                .website(createUserRequest.getWebsite())
                .build();

        final com.alonso360rn.blockingexample.services.facades.models.User createUserResponse
                = usersFacade.createUser(user);

        return User.builder()
                .address(getFormattedAddress(createUserResponse.getAddress()))
                .id(createUserResponse.getId())
                .name(createUserResponse.getName())
                .build();
    }

    private String getFormattedAddress(Address address) {

        return String.format("%s %s, %s", address.getSuite(), address.getStreet(), address.getCity());
    }
}
