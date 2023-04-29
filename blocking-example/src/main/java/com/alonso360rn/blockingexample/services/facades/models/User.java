package com.alonso360rn.blockingexample.services.facades.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {

    private Address address;

    private Company company;

    private String email;

    private String id;

    private String name;

    private String phone;

    private String username;

    private String website;
}
