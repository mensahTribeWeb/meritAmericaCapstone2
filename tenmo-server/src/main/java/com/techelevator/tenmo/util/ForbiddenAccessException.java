package com.techelevator.tenmo.util;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class ForbiddenAccessException extends Exception{

    @Override
    public String getMessage() {
        return "Cannot access other users' information";
    }
}
