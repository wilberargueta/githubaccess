package com.coliand.test.githubaccess.rest.controller;

import java.util.List;
import java.util.Map;

import com.coliand.test.githubaccess.rest.model.RepositoryRequestsBody;
import com.coliand.test.githubaccess.rest.service.RepositoryService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/repositories")
public class RepositoryController {

    private final RepositoryService repositoryService;

    public RepositoryController(RepositoryService repositoryService) {
        this.repositoryService = repositoryService;
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> create(@RequestBody RepositoryRequestsBody requestsBody) {

        return new ResponseEntity<Map<String, Object>>(this.repositoryService.create(requestsBody), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Map<String, Object>>> get(@RequestBody Map<String, String> requestsBody) {

        return new ResponseEntity<List<Map<String, Object>>>(this.repositoryService.get(requestsBody), HttpStatus.OK);
    }

    @PatchMapping(path = "/{repositoryName}")
    public ResponseEntity<Map<String, Object>> update(@RequestBody Map<String, Object> requestsBody,
            @PathVariable String repositoryName) {

        return new ResponseEntity<Map<String, Object>>(this.repositoryService.update(requestsBody, repositoryName),
                HttpStatus.OK);
    }
}
