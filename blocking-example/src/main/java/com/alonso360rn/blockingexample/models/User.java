package com.alonso360rn.blockingexample.models;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class User {

    private String id;

    private String name;

    private String address;
}
