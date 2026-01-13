package com.shpyakin.javassau.service;

import com.shpyakin.javassau.model.AuditLog;
import com.shpyakin.javassau.repository.AuditLogRepository;
import com.shpyakin.javassau.model.ChangeType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class AuditService {
    private final AuditLogRepository auditLogRepository;

    @Transactional
    public void logEvent(String entityType, Long entityId, ChangeType action, String changedBy) {
        AuditLog log = new AuditLog();
        log.setEntityType(entityType);
        log.setEntityId(entityId);
        log.setAction(action.name());
        log.setChangedBy(changedBy);
        log.setChangedAt(new Date());
        auditLogRepository.save(log);
    }
}