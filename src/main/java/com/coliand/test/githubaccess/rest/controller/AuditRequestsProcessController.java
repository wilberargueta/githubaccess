package com.coliand.test.githubaccess.rest.controller;

import java.util.List;

import com.coliand.test.githubaccess.rest.model.AuditRequestsProcess;
import com.coliand.test.githubaccess.rest.service.AuditRequestsProcessService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/audit")
public class AuditRequestsProcessController {

    private final AuditRequestsProcessService auditRequestsProcessService;

    public AuditRequestsProcessController(AuditRequestsProcessService auditRequestsProcessService) {
        this.auditRequestsProcessService = auditRequestsProcessService;
    }

    @GetMapping
    public ResponseEntity<List<AuditRequestsProcess>> getAll() {

        return new ResponseEntity<List<AuditRequestsProcess>>(this.auditRequestsProcessService.getAll(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<AuditRequestsProcess> getById(@PathVariable Long id) {

        return new ResponseEntity<AuditRequestsProcess>(this.auditRequestsProcessService.getById(id), HttpStatus.OK);
    }
}
