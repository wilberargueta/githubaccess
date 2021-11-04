package com.coliand.test.githubaccess.rest.model;

import java.time.LocalDateTime;

public class AuditRequestsProcess {
    private Long id;

    private String url;

    private LocalDateTime date;

    private String httpMethod;

    private String requestParams;

    private String responseCode;

    public AuditRequestsProcess() {
    }

    public AuditRequestsProcess(Long id, String url, LocalDateTime date, String httpMethod, String requestParams,
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
        return "AuditRequestsProcess [date=" + date + ", httpMethod=" + httpMethod + ", id=" + id + ", requestParams="
                + requestParams + ", responseCode=" + responseCode + ", url=" + url + "]";
    }

}
