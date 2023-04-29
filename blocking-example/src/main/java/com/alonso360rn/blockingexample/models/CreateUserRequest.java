package com.alonso360rn.blockingexample.models;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateUserRequest {

    @NotNull
    @Valid
    private CreateUserAddress address;

    @NotNull
    @Valid
    private CreateUserCompany company;

    @NotEmpty
    private String email;

    @NotEmpty
    private String name;

    @NotEmpty
    private String phone;

    @NotEmpty
    private String username;

    @NotEmpty
    private String website;
}
