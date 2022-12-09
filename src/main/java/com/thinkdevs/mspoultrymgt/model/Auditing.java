package com.thinkdevs.mspoultrymgt.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class Auditing {

    @CreatedBy
    @Column(updatable = false)
    @JsonIgnore
    protected String createdBy;

    @CreatedDate
    @Column(updatable = false)
    protected LocalDateTime dateCreated;

    @LastModifiedBy
    @JsonIgnore
    protected String lastModifiedBy;

    @LastModifiedDate
    @JsonIgnore
    protected LocalDateTime lastModifiedDate;
}
