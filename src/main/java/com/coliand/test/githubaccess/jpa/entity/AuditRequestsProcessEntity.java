package com.coliand.test.githubaccess.jpa.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "AUDIT_REQUESTS_PROCESS")
public class AuditRequestsProcessEntity {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "url", nullable = false)
    private String url;

    @Column(name = "DATE", nullable = false)
    private LocalDateTime date;

    @Column(name = "HTTP_METHOD", nullable = false)
    private String httpMethod;

    @Column(name = "REQUEST_PARAMS", nullable = true)
    private String requestParams;

    @Column(name = "RESPONSE_CODE", nullable = false)
     String responseCode;

    public AuditRequestsProcessEntity() {
    }

    public AuditRequestsProcessEntity(Long id, String url, LocalDateTime date, String httpMethod, String requestParams,
            String responseCode) {
        this.id = id;
        this.url = url;
        this.date = date;
        this.httpMethod = httpMethod;
        this.requestParams = requestParams;
        this.responseCode = responseCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }

    public String getRequestParams() {
        return requestParams;
    }

    public void setRequestParams(String requestParams) {
        this.requestParams = requestParams;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    @Override
    public String toString() {
        return "AuditRequestsProcessEntity [date=" + date + ", httpMethod=" + httpMethod + ", id=" + id
                + ", requestParams=" + requestParams + ", responseCode=" + responseCode + ", url=" + url + "]";
    }

}
