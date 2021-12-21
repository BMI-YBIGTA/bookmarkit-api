package com.bmi.bookmarkitapi.common;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
public abstract class BaseCommandService<T extends BaseEntity> {
    private final BaseRepository<T> repository;

    public T save(T entity) {
        return repository.save(entity);
    }

    public void delete(T entity) {
        repository.delete(entity);
    }
}
