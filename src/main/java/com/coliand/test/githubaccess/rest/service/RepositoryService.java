package com.coliand.test.githubaccess.rest.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import com.coliand.test.githubaccess.config.MapperConfig;
import com.coliand.test.githubaccess.rest.model.AuditRequestsProcess;
import com.coliand.test.githubaccess.rest.model.RepositoryRequestsBody;
import com.coliand.test.githubaccess.util.exceptions.BadRequestsException;
import com.coliand.test.githubaccess.util.exceptions.ErrorRequestsException;

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
public class RepositoryService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    public final RestTemplate restTemplate;
    private final AuditRequestsProcessService auditRequestsProcessService;

    private final MapperConfig mapperConfig;

    @Value("${app.github.access-token}")
    private String accessToken;

    public RepositoryService(RestTemplate restTemplate, AuditRequestsProcessService auditRequestsProcessService,
            MapperConfig mapperConfig) {
        this.restTemplate = restTemplate;
        this.auditRequestsProcessService = auditRequestsProcessService;
        this.mapperConfig = mapperConfig;
    }

    public Map<String, Object> create(RepositoryRequestsBody repositoryRequestsBody) {

        if (repositoryRequestsBody == null)
            throw new BadRequestsException("Campos requeridos no encontrados");

        final String URL = "https://api.github.com/user/repos";

        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        requestHeaders.set("Authorization", "token " + repositoryRequestsBody.getAccessToken());

        HttpEntity<RepositoryRequestsBody> request = new HttpEntity<>(repositoryRequestsBody, requestHeaders);
        ResponseEntity<Map<String, Object>> response = null;

        AuditRequestsProcess auditRequestsProcess = new AuditRequestsProcess();
        auditRequestsProcess.setDate(LocalDateTime.now());
        auditRequestsProcess.setHttpMethod("POST");

        auditRequestsProcess.setUrl(URL);
        try {
            auditRequestsProcess
                    .setRequestParams(this.mapperConfig.objectMapper().writeValueAsString(repositoryRequestsBody));

            response = restTemplate.exchange(URL, HttpMethod.POST, request,
                    new ParameterizedTypeReference<Map<String, Object>>() {
                    });
            auditRequestsProcess.setResponseCode(response.getStatusCode().toString());
        } catch (HttpClientErrorException e) {

            if (e.getStatusCode() == HttpStatus.BAD_REQUEST)
                throw new BadRequestsException("No se pudo crear el repositorio, los parametros estan incorrectos.");

            if (e.getStatusCode() == HttpStatus.UNAUTHORIZED)
                throw new BadRequestsException("No se pudo crear el repositorio, token de accedo invalido.");
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

    public List<Map<String, Object>> get(Map<String, String> requestBody) {

        if (requestBody == null || !requestBody.containsKey("accessToken"))
            throw new BadRequestsException("Campos requeridos no encontrados");

        final String URL = "https://api.github.com/user/repos";

        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        requestHeaders.set("Authorization", "token " + requestBody.get("accessToken"));

        HttpEntity<?> request = new HttpEntity<>(requestHeaders);
        ResponseEntity<List<Map<String, Object>>> response = null;
        AuditRequestsProcess auditRequestsProcess = new AuditRequestsProcess();
        auditRequestsProcess.setDate(LocalDateTime.now());
        auditRequestsProcess.setHttpMethod("GET");

        auditRequestsProcess.setUrl(URL);

        try {

            auditRequestsProcess.setRequestParams(this.mapperConfig.objectMapper().writeValueAsString(requestBody));

            response = restTemplate.exchange(URL, HttpMethod.GET, request,
                    new ParameterizedTypeReference<List<Map<String, Object>>>() {
                    });

        } catch (HttpClientErrorException e) {

            if (e.getStatusCode() == HttpStatus.UNAUTHORIZED)
                throw new BadRequestsException("No se pudo consultar los repositorios, token de accedo invalido.");
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

    public Map<String, Object> update(Map<String, Object> requestBody, String repositoryName) {

        if (requestBody == null || !requestBody.containsKey("accessToken") || !requestBody.containsKey("username")
                || !requestBody.containsKey("name") || !requestBody.containsKey("privateRepository"))
            throw new BadRequestsException("Campos requeridos no encontrados");

        final String URL = "https://api.github.com/repos/" + requestBody.get("username") + "/" + repositoryName;

        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        requestHeaders.set("Authorization", "token " + requestBody.get("accessToken"));
        requestHeaders.set("X-HTTP-Method-Override", "PATCH");

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestBody, requestHeaders);
        ResponseEntity<Map<String, Object>> response = null;
        AuditRequestsProcess auditRequestsProcess = new AuditRequestsProcess();
        auditRequestsProcess.setDate(LocalDateTime.now());
        auditRequestsProcess.setHttpMethod("PATCH");

        auditRequestsProcess.setUrl(URL);

        try {
            response = restTemplate.exchange(URL, HttpMethod.POST, request,
                    new ParameterizedTypeReference<Map<String, Object>>() {
                    });
            auditRequestsProcess.setResponseCode(response.getStatusCode().toString());
        } catch (HttpClientErrorException e) {

            if (e.getStatusCode() == HttpStatus.UNAUTHORIZED)
                throw new BadRequestsException("No se pudo crear el repositorio, token de accedo invalido.");
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
