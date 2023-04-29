package com.alonso360rn.blockingexample.models;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateUserCompany {

    private String bs;

    private String catchPhrase;

    private String name;
}
