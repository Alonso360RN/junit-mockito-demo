package com.alonso360rn.services;

import com.alonso360rn.blockingexample.models.CreateUserAddress;
import com.alonso360rn.blockingexample.models.CreateUserCompany;
import com.alonso360rn.blockingexample.models.CreateUserGeo;
import com.alonso360rn.blockingexample.models.CreateUserRequest;
import com.alonso360rn.blockingexample.services.facades.models.Address;
import com.alonso360rn.blockingexample.services.facades.models.Company;
import com.alonso360rn.blockingexample.services.facades.models.Geo;
import com.alonso360rn.blockingexample.services.facades.models.User;

import java.util.List;

public abstract class BaseTest {

    protected User getUser(String id) {

        final Geo geo = Geo.builder()
                .lat("lat")
                .lng("lng")
                .build();

        final Address address = Address.builder()
                .city("city")
                .geo(geo)
                .street("street")
                .suite("suite")
                .build();

        final Company company = Company.builder()
                .bs("bs")
                .catchPhrase("catchPhrase")
                .name("name")
                .build();

        return User.builder()
                .address(address)
                .company(company)
                .email("john.wick@gmail.com")
                .id(id)
                .name("John Wick")
                .phone("12345678")
                .username("john_wick")
                .website("website.com")
                .build();
    }

    protected List<User> getUsersList() {

        final Address address1 = Address.builder()
                .city("city 1")
                .street("street 1")
                .suite("suite 1")
                .build();

        final Address address2 = Address.builder()
                .city("city 2")
                .street("street 2")
                .suite("suite 2")
                .build();

        final User user1 = User.builder()
                .address(address1)
                .id("1")
                .name("name 1")
                .build();

        final User user2 = User.builder()
                .address(address2)
                .id("2")
                .name("name 2")
                .build();

        return List.of(user1, user2);
    }

    protected CreateUserRequest getCreateUserRequest() {

        CreateUserCompany createUserCompany = CreateUserCompany.builder()
                .bs("bs")
                .catchPhrase("catchPhrase")
                .name("name")
                .build();

        CreateUserGeo createUserGeo = CreateUserGeo.builder()
                .lat("lat")
                .lng("lng")
                .build();

        CreateUserAddress createUserAddress = CreateUserAddress.builder()
                .city("city")
                .geo(createUserGeo)
                .street("street")
                .suite("suite")
                .zipCode("zipCode")
                .build();

        return CreateUserRequest.builder()
                .address(createUserAddress)
                .company(createUserCompany)
                .email("john.wick@gmail.com")
                .name("John Wick")
                .phone("12345678")
                .username("john_wick")
                .website("website.com")
                .build();
    }
}
