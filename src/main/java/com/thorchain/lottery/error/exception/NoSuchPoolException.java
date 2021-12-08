package com.thorchain.lottery.error.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class NoSuchPoolException extends ResponseStatusException {
    public NoSuchPoolException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }
}