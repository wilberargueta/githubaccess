package com.coliand.test.githubaccess.rest.service;

import java.time.LocalDateTime;
import java.util.Map;

import com.coliand.test.githubaccess.rest.model.AuditRequestsProcess;
import com.coliand.test.githubaccess.util.exceptions.BadRequestsException;
import com.coliand.test.githubaccess.util.exceptions.ErrorRequestsException;
import com.coliand.test.githubaccess.util.exceptions.ObjectsNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class UsersService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    public final RestTemplate restTemplate;
    private final AuditRequestsProcessService auditRequestsProcessService;

    @Value("${app.github.access-token}")
    private String accessToken;

    public UsersService(RestTemplate restTemplate, AuditRequestsProcessService auditRequestsProcessService) {
        this.restTemplate = restTemplate;
        this.auditRequestsProcessService = auditRequestsProcessService;
    }

    public Map<String, Object> getUsers(String username) {

        if (username == null)
            throw new BadRequestsException("La solicitud  debe contener un usuario.");

        final String URL = "https://api.github.com/users/" + username;

        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        requestHeaders.setBasicAuth(username, this.accessToken);

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestHeaders);
        ResponseEntity<Map<String, Object>> response = null;

        AuditRequestsProcess auditRequestsProcess = new AuditRequestsProcess();
        auditRequestsProcess.setDate(LocalDateTime.now());
        auditRequestsProcess.setHttpMethod("GET");

        auditRequestsProcess.setUrl(URL);
        try {
            response = restTemplate.exchange(URL, HttpMethod.GET, request,
                    new ParameterizedTypeReference<Map<String, Object>>() {
                    });
            auditRequestsProcess.setResponseCode(response.getStatusCode().toString());
            if (response.getStatusCode() == HttpStatus.NOT_FOUND)
                throw new ObjectsNotFoundException("El usuario no fue encontrado.");

        } catch (HttpClientErrorException e) {

            if (e.getStatusCode() == HttpStatus.NOT_FOUND)
                throw new ObjectsNotFoundException("El usuario no fue encontrado.");
            auditRequestsProcess.setResponseCode(e.getStatusText());
            throw new ErrorRequestsException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            this.logger.debug("ERROR: {}", response);

            this.logger.debug("Error en consumiendo la api {} por los siguientes problemas => {}", URL, e.getMessage());
            auditRequestsProcess.setResponseCode("500");
            throw new ErrorRequestsException("No fue posible consultar la API debido a problemas internos.");
        } finally {
            this.auditRequestsProcessService.save(auditRequestsProcess);
        }

        return response.getBody();
    }
}
