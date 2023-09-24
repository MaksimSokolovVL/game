package com.game.domain.model;

import org.springframework.http.HttpStatus;

public class WebError {
    private final HttpStatus status;

    private final String message;


    public WebError(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
