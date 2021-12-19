package com.bmi.bookmarkitapi.common;

import com.bmi.bookmarkitapi.common.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

@RequiredArgsConstructor
public abstract class BaseQueryService<T extends BaseEntity> {
    private final BaseRepository<T> repository;
    private final NotFoundException notFoundException;

    public Page<T> query(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public List<T> query() {
        return repository.findAll();
    }

    public T query(Long id) {
        return repository.findById(id).orElseThrow(() -> notFoundException);
    }
}
