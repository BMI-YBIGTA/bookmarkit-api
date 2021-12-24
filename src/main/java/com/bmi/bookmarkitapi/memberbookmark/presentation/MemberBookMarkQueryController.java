package com.bmi.bookmarkitapi.memberbookmark.presentation;

import com.bmi.bookmarkitapi.common.BaseQueryController;
import com.bmi.bookmarkitapi.common.dto.Response;
import com.bmi.bookmarkitapi.common.security.JwtTokenProvider;
import com.bmi.bookmarkitapi.memberbookmark.application.MemberBookMarkCategoryQueryService;
import com.bmi.bookmarkitapi.memberbookmark.application.MemberBookMarkRecentQueryService;
import com.bmi.bookmarkitapi.memberbookmark.application.MemberBookMarkSearchService;
import com.bmi.bookmarkitapi.memberbookmark.application.model.*;
import com.bmi.bookmarkitapi.memberbookmark.domain.model.MemberBookMark;
import com.bmi.bookmarkitapi.memberbookmark.domain.service.MemberBookMarkQueryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/memberbookmark")
public class MemberBookMarkQueryController extends BaseQueryController<MemberBookMark> {
    private final MemberBookMarkSearchService searchService;
    private final MemberBookMarkCategoryQueryService categoryQueryService;
    private final MemberBookMarkRecentQueryService recentQueryService;
    private final JwtTokenProvider jwtTokenProvider;

    public MemberBookMarkQueryController(
            MemberBookMarkQueryService queryService,
            MemberBookMarkSearchService searchService,
            MemberBookMarkCategoryQueryService categoryQueryService,
            MemberBookMarkRecentQueryService recentQueryService,
            JwtTokenProvider jwtTokenProvider
    ) {
        super(queryService);
        this.searchService = searchService;
        this.categoryQueryService = categoryQueryService;
        this.recentQueryService = recentQueryService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @GetMapping("/search")
    public Response.Page<BookMarkSearchDto> search(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "100") int size,
            @RequestParam(name = "query") String searchText,
            HttpServletRequest httpServletRequest
    ){
        Long id = jwtTokenProvider.getMemberId(jwtTokenProvider.extractToken(httpServletRequest));
        Page<BookMarkSearchDto> tempPage =
                searchService.search(new MemberBookMarkSearchRequest(id, searchText), PageRequest.of(page, size));

        return new Response.Page<>(tempPage.getContent(), tempPage.getTotalPages());
    }

    @GetMapping("/query")
    public Response.ItemList<MemberBookMarkCategoryQueryDto> query(
            @RequestParam(name = "category" , defaultValue = "") String category,
            HttpServletRequest httpServletRequest
    ){
        Long id = jwtTokenProvider.getMemberId(jwtTokenProvider.extractToken(httpServletRequest));
        MemberBookMarkQueryRequest queryRequest;
        if (category.isEmpty()){
            queryRequest = new MemberBookMarkQueryRequest(id);
        }
        else{
            queryRequest = new MemberBookMarkQueryRequest(id,category);
        }
        List<MemberBookMarkCategoryQueryDto> list = categoryQueryService.query(queryRequest);
        return new Response.ItemList<>(list);
    }

    @GetMapping("/recent")
    public Response.ItemList<BookMarkQueryDto> query(
            HttpServletRequest httpServletRequest
    ){
        Long id = jwtTokenProvider.getMemberId(jwtTokenProvider.extractToken(httpServletRequest));
        MemberBookMarkQueryRequest queryRequest = new MemberBookMarkQueryRequest(id);
        List<BookMarkQueryDto> list = recentQueryService.query(queryRequest);

        return new Response.ItemList<>(list);
    }

}
