package com.coliand.test.githubaccess.rest.controller;

import java.util.Map;

import javax.validation.constraints.Min;

import com.coliand.test.githubaccess.rest.service.UsersService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/users")
public class UsersController {

    private final UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping(path = "/{username}")
    public ResponseEntity<Map<String, Object>> getByEnable(
            @PathVariable @Min(value = 3, message = "Debe ingresar un usuario de 3 caracteres minimo.") String username) {

        return new ResponseEntity<Map<String, Object>>(this.usersService.getUsers(username), HttpStatus.OK);
    }

}
