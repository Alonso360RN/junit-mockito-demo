package com.alonso360rn.blockingexample.controllers;

import com.alonso360rn.blockingexample.models.CreateUserRequest;
import com.alonso360rn.blockingexample.models.User;
import com.alonso360rn.blockingexample.models.common.BaseResponse;
import com.alonso360rn.blockingexample.services.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
public class UsersController {

    private final UsersService usersService;

    @GetMapping(value = "/")
    public ResponseEntity<BaseResponse<List<User>>> getUsers() {

        final HttpStatus httpStatus = HttpStatus.OK;

        final BaseResponse<List<User>> baseResponse = getBaseResponse(usersService.getUsers(), httpStatus.value());

        return new ResponseEntity<>(baseResponse, httpStatus);
    }

    @PostMapping(value = "/")
    public ResponseEntity<BaseResponse<User>> createUser(@RequestBody CreateUserRequest createUserRequest) {

        final HttpStatus httpStatus = HttpStatus.CREATED;

        final BaseResponse<User> baseResponse = getBaseResponse(usersService.createUser(createUserRequest), httpStatus.value());

        return new ResponseEntity<>(baseResponse, httpStatus);
    }

    private BaseResponse getBaseResponse(Object data, int status) {

        return BaseResponse.builder()
                .data(data)
                .status(status)
                .build();
    }
}
