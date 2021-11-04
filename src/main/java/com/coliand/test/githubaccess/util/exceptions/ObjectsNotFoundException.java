package com.coliand.test.githubaccess.util.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ObjectsNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ObjectsNotFoundException(String message) {
        super(message);
    }
}
