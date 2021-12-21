package com.bmi.bookmarkitapi.common;

import com.bmi.bookmarkitapi.common.dto.Response;
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
    Response.ItemList<T> queryAll() {
        List<T> list = queryService.query();
        return new Response.ItemList<T>(list);
    }

    @GetMapping("/page")
    Response.Page<T> queryAllByPage(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "100") int size
    ) {
        Page<T> tempPage = queryService.query(
                PageRequest.of(page,size).withSort(Sort.Direction.DESC, "createdDate")
        );
        return new Response.Page<T>(tempPage.getContent(), tempPage.getTotalPages());
    }

    @GetMapping("/{id}")
    Response.Item<T> queryOne(
            @PathVariable Long id
    ) {
        T item = queryService.query(id);
        return new Response.Item<T>(item);
    }
}
