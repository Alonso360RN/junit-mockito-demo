package com.alonso360rn.blockingexample.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreateUserGeo {

    @NotEmpty
    private String lat;

    @NotEmpty
    private String lng;
}
