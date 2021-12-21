package com.bmi.bookmarkitapi.memberbookmark.presentation;

import com.bmi.bookmarkitapi.common.BaseQueryController;
import com.bmi.bookmarkitapi.memberbookmark.application.MemberBookMarkSearchService;
import com.bmi.bookmarkitapi.memberbookmark.domain.model.MemberBookMarkSearchRequest;
import com.bmi.bookmarkitapi.memberbookmark.domain.model.MemberBookMarkSearchResponse;
import com.bmi.bookmarkitapi.memberbookmark.domain.model.MemberBookmark;
import com.bmi.bookmarkitapi.memberbookmark.domain.service.MemberBookMarkQueryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/memberbookmark")
public class MemberBookMarkQueryController extends BaseQueryController<MemberBookmark> {
    private final MemberBookMarkSearchService searchService;
    public MemberBookMarkQueryController(
            MemberBookMarkQueryService queryService,MemberBookMarkSearchService searchService
    ) {
        super(queryService);
        this.searchService = searchService;
    }

    @GetMapping("/{id}/search")
    public Page<MemberBookMarkSearchResponse> search(
            @PathVariable Long id,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "100") int size,
            @RequestParam(name = "query") String searchText
    ){
        return searchService.search(new MemberBookMarkSearchRequest(id,searchText), PageRequest.of(page,size));
    }
}
