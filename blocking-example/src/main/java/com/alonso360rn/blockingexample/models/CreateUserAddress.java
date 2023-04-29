package com.alonso360rn.blockingexample.models;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateUserAddress {

    @NotEmpty
    private String city;

    @NotNull
    @Valid
    private CreateUserGeo geo;

    @NotEmpty
    private String street;

    @NotEmpty
    private String suite;

    @NotEmpty
    private String zipCode;
}
