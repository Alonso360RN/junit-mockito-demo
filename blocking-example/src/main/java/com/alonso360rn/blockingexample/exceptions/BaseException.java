package com.alonso360rn.blockingexample.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BaseException extends RuntimeException {

    protected String errorCode;

    protected String errorMessage;

    protected int httpCode = HttpStatus.INTERNAL_SERVER_ERROR.value();

    public BaseException(final String errorCode, final String errorMessage, final Exception e) {

        super(errorMessage, e);

        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public BaseException(final String errorCode, final String errorMessage, final int httpCode, final Exception e) {

        this(errorCode, errorMessage, e);

        this.httpCode = httpCode;
    }
}
