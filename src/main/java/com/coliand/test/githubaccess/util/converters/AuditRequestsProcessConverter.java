package com.coliand.test.githubaccess.util.converters;

import com.coliand.test.githubaccess.jpa.entity.AuditRequestsProcessEntity;
import com.coliand.test.githubaccess.rest.model.AuditRequestsProcess;

import org.springframework.stereotype.Component;

@Component
public class AuditRequestsProcessConverter {
    public AuditRequestsProcess entityToModel(AuditRequestsProcessEntity entity) {
        AuditRequestsProcess model = new AuditRequestsProcess();
        model.setId(entity.getId());
        model.setDate(entity.getDate());
        model.setHttpMethod(entity.getHttpMethod());
        model.setRequestParams(entity.getRequestParams());
        model.setResponseCode(entity.getResponseCode());
        model.setUrl(entity.getUrl());

        return model;
    }

    public AuditRequestsProcessEntity modelToEntity(AuditRequestsProcess model) {
        AuditRequestsProcessEntity entity = new AuditRequestsProcessEntity();

        entity.setId(model.getId());
        entity.setDate(model.getDate());
        entity.setHttpMethod(model.getHttpMethod());
        entity.setRequestParams(model.getRequestParams());
        entity.setResponseCode(model.getResponseCode());
        entity.setUrl(model.getUrl());
        return entity;
    }
}
