package com.alonso360rn.blockingexample.exceptions;

import lombok.Getter;

@Getter
public class JsonPlaceholderUpstreamException extends BaseException {

    public JsonPlaceholderUpstreamException(Exception e) {
        super(ErrorCodes.JSON_PLACEHOLDER_UPSTREAM_ERROR, "An upstream json placeholder service failed.", e);
    }
}
