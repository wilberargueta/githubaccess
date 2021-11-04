package com.coliand.test.githubaccess.util.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.EXPECTATION_FAILED)
public class ErrorRequestsException extends RuntimeException{
    
    private static final long serialVersionUID = 1L;

    public ErrorRequestsException(String message) {
        super(message);
    }
}
