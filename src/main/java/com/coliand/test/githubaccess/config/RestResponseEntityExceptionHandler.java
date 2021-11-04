package com.coliand.test.githubaccess.config;

import java.util.HashMap;
import java.util.Map;

import com.coliand.test.githubaccess.util.exceptions.BadRequestsException;
import com.coliand.test.githubaccess.util.exceptions.ErrorRequestsException;
import com.coliand.test.githubaccess.util.exceptions.ObjectsNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * RestResponseEntityExceptionHandler
 */
@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ ObjectsNotFoundException.class })
    public ResponseEntity<Map<String, Object>> handleObjectNotFOund(Exception ex, WebRequest request) {
        Map<String, Object> response = new HashMap<>();
        response.put("OK", false);
        response.put("message", ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ExceptionHandler({ ErrorRequestsException.class })
    public ResponseEntity<Map<String, Object>> handleObjectErrorRequests(Exception ex, WebRequest request) {
        Map<String, Object> response = new HashMap<>();
        response.put("OK", false);
        response.put("message", ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ExceptionHandler({ BadRequestsException.class })
    public ResponseEntity<Map<String, Object>> handleObjectBabRequests(Exception ex, WebRequest request) {
        Map<String, Object> response = new HashMap<>();
        response.put("OK", false);
        response.put("message", ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // @ExceptionHandler({ HttpMessageNotReadableException.class })
    // public ResponseEntity<Map<String, Object>> handleObjectBabRequestsHttp(Exception ex, WebRequest request) {
    //     Map<String, Object> response = new HashMap<>();
    //     response.put("OK", false);
    //     response.put("message", "No se encontro cuerpo de la solicitud.");
    //     return new ResponseEntity<>(response, HttpStatus.OK);
    // }
}