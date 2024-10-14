package org.acme.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;

import java.util.Date;

@MappedSuperclass
abstract class AbstractFullEntity extends AbstractEntity {

    @JsonIgnore
    @Column(
            name = "created_at",
            nullable = false,
            updatable = false,
            columnDefinition = "timestamp with time zone not null"
    )
    public Date createdAt;

    @Column(
            name = "updated_at",
            nullable = false,
            columnDefinition = "timestamp with time zone not null"
    )
    public Date updatedAt;

    @JsonIgnore
    @Column(
            name = "deleted_at",
            nullable = false,
            columnDefinition = "timestamp with time zone"
    )
    public Date deletedAt = new Date(0);

}