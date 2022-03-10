package com.bmi.bookmarkitapi.common;

import lombok.RequiredArgsConstructor;

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
