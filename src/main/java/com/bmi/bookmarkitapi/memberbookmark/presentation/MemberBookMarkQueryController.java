package com.bmi.bookmarkitapi.memberbookmark.presentation;

import com.bmi.bookmarkitapi.common.BaseQueryController;
import com.bmi.bookmarkitapi.common.dto.Response;
import com.bmi.bookmarkitapi.memberbookmark.application.MemberBookMarkCategoryQueryService;
import com.bmi.bookmarkitapi.memberbookmark.application.MemberBookMarkRecentQueryService;
import com.bmi.bookmarkitapi.memberbookmark.application.MemberBookMarkSearchService;
import com.bmi.bookmarkitapi.memberbookmark.application.model.*;
import com.bmi.bookmarkitapi.memberbookmark.domain.model.MemberBookMark;
import com.bmi.bookmarkitapi.memberbookmark.domain.service.MemberBookMarkQueryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/api/memberbookmark")
public class MemberBookMarkQueryController extends BaseQueryController<MemberBookMark> {
    private final MemberBookMarkSearchService searchService;
    private final MemberBookMarkCategoryQueryService categoryQueryService;
    private final MemberBookMarkRecentQueryService recentQueryService;

    public MemberBookMarkQueryController(
            MemberBookMarkQueryService queryService,
            MemberBookMarkSearchService searchService,
            MemberBookMarkCategoryQueryService categoryQueryService,
            MemberBookMarkRecentQueryService recentQueryService
    ) {
        super(queryService);
        this.searchService = searchService;
        this.categoryQueryService = categoryQueryService;
        this.recentQueryService = recentQueryService;
    }

    @GetMapping("/{id}/search")
    public Response.Page<BookMarkSearchDto> search(
            @PathVariable Long id,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "100") int size,
            @RequestParam(name = "query") String searchText
    ){
        Page<BookMarkSearchDto> tempPage =
                searchService.search(new MemberBookMarkSearchRequest(id, searchText), PageRequest.of(page, size));

        return new Response.Page<>(tempPage.getContent(), tempPage.getTotalPages());
    }

    @GetMapping("/{id}/query")
    public Response.Item<Map<String, Map<String, List<BookMarkQueryDto>>>> query(
            @PathVariable Long id,
            @RequestParam(name = "category" , defaultValue = "") String category
    ){
        MemberBookMarkQueryRequest queryRequest;
        if (Objects.equals(category, "")){
            queryRequest = new MemberBookMarkQueryRequest(id);
        }
        else{
            queryRequest = new MemberBookMarkQueryRequest(id,category);
        }
        Map<String, Map<String, List<BookMarkQueryDto>>> map = categoryQueryService.query(queryRequest);
        return new Response.Item<>(map);
    }

    @GetMapping("/{id}/recent")
    public Response.ItemList<BookMarkQueryDto> query(
            @PathVariable Long id
    ){
        MemberBookMarkQueryRequest queryRequest = new MemberBookMarkQueryRequest(id);
        List<BookMarkQueryDto> list = recentQueryService.query(queryRequest);

        return new Response.ItemList<BookMarkQueryDto>(list);
    }

}
