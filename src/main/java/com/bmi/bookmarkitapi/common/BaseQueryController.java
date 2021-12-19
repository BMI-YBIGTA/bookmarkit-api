package com.bmi.bookmarkitapi.common;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequiredArgsConstructor
abstract public class BaseQueryController<T extends BaseEntity> {
    private final BaseQueryService<T> queryService;

    @GetMapping
    List<T> queryAll() {
        return queryService.query();
    }

    @GetMapping("/page")
    Page<T> queryAllByPage(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "100") int size
    ) {
        return queryService.query(
                PageRequest.of(page,size).withSort(Sort.Direction.DESC, "createdDate")
        );
    }

    @GetMapping("/{id}")
    T queryOne(
            @PathVariable Long id
    ) {
        return queryService.query(id);
    }
}
