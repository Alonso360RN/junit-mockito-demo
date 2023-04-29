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
public class Address {

    private String city;

    private Geo geo;

    private String street;

    private String suite;

    private String zipCode;
}
