package com.bmi.bookmarkitapi.common;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public abstract class BaseEntity extends AbstractAggregateRoot<BaseEntity> {
    @CreatedDate
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime modifiedDate;

    public String createDateToString(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd");
        return this.createdDate.format(formatter);
    }
}
