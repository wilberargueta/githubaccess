package com.coliand.test.githubaccess.rest.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.coliand.test.githubaccess.jpa.entity.AuditRequestsProcessEntity;
import com.coliand.test.githubaccess.jpa.repository.AuditRequestsProcessRepository;
import com.coliand.test.githubaccess.rest.model.AuditRequestsProcess;
import com.coliand.test.githubaccess.util.converters.AuditRequestsProcessConverter;
import com.coliand.test.githubaccess.util.exceptions.BadRequestsException;
import com.coliand.test.githubaccess.util.exceptions.ObjectsNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class AuditRequestsProcessService {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final AuditRequestsProcessRepository auditRequestsProcessRepository;
    private final AuditRequestsProcessConverter auditRequestsProcessConverter;

    public AuditRequestsProcessService(AuditRequestsProcessRepository auditRequestsProcessRepository,
            AuditRequestsProcessConverter auditRequestsProcessConverter) {
        this.auditRequestsProcessRepository = auditRequestsProcessRepository;
        this.auditRequestsProcessConverter = auditRequestsProcessConverter;
    }

    public AuditRequestsProcess save(AuditRequestsProcess auditRequestsProcess) {
        return this.auditRequestsProcessConverter.entityToModel(this.auditRequestsProcessRepository
                .save(this.auditRequestsProcessConverter.modelToEntity(auditRequestsProcess)));
    }

    public AuditRequestsProcess getById(Long id) {

        if (id == null)
            throw new BadRequestsException("Debe especificar un id");

        this.logger.debug("OBTENIENDO REGISTRO POR ID = {}", id);
        
        Optional<AuditRequestsProcessEntity> optional = this.auditRequestsProcessRepository.findById(id);
        if (!optional.isPresent())
            throw new ObjectsNotFoundException("El registro no fue encontrado");

        return this.auditRequestsProcessConverter.entityToModel(optional.get());
    }

    public List<AuditRequestsProcess> getAll() {
        return this.auditRequestsProcessRepository.findAll().stream()
                .map(this.auditRequestsProcessConverter::entityToModel).collect(Collectors.toList());
    }
}
