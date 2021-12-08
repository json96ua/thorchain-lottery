package com.thorchain.lottery.error.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class NoValidPoolsForPlayerException extends ResponseStatusException {
    public NoValidPoolsForPlayerException(String message){
        super(HttpStatus.NOT_FOUND, message);
    }
}