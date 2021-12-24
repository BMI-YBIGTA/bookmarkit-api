package com.bmi.bookmarkitapi.memberbookmark.presentation;

import com.bmi.bookmarkitapi.common.BaseQueryController;
import com.bmi.bookmarkitapi.common.dto.Response;
import com.bmi.bookmarkitapi.common.security.JwtTokenProvider;
import com.bmi.bookmarkitapi.memberbookmark.application.MemberBookMarkCategoryQueryService;
import com.bmi.bookmarkitapi.memberbookmark.application.MemberBookMarkRecentQueryService;
import com.bmi.bookmarkitapi.memberbookmark.application.MemberBookMarkSearchService;
import com.bmi.bookmarkitapi.memberbookmark.application.model.BookMarkQueryDto;
import com.bmi.bookmarkitapi.memberbookmark.application.model.BookMarkSearchDto;
import com.bmi.bookmarkitapi.memberbookmark.application.model.MemberBookMarkQueryRequest;
import com.bmi.bookmarkitapi.memberbookmark.application.model.MemberBookMarkSearchRequest;
import com.bmi.bookmarkitapi.memberbookmark.domain.model.MemberBookMark;
import com.bmi.bookmarkitapi.memberbookmark.domain.service.MemberBookMarkQueryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

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
    ) {
        Long id = jwtTokenProvider.getMemberId(jwtTokenProvider.extractToken(httpServletRequest));

        Page<BookMarkSearchDto> tempPage = searchService.search(
                new MemberBookMarkSearchRequest(id, searchText), PageRequest.of(page, size)
        );

        return new Response.Page<>(tempPage.getContent(), tempPage.getTotalPages());
    }

    @GetMapping("/query")
    public Response.Item<Map<String, Map<String, List<BookMarkQueryDto>>>> query(
            @RequestParam(name = "category", defaultValue = "") String category,
            HttpServletRequest httpServletRequest
    ) {
        Long id = jwtTokenProvider.getMemberId(jwtTokenProvider.extractToken(httpServletRequest));

        return new Response.Item<>(categoryQueryService.query(new MemberBookMarkQueryRequest(id, category)));
    }

    @GetMapping("/recent")
    public Response.Item<Map<String,List<BookMarkQueryDto>>> query(
            HttpServletRequest httpServletRequest
    ) {
        Long id = jwtTokenProvider.getMemberId(jwtTokenProvider.extractToken(httpServletRequest));

        return new Response.Item<>(recentQueryService.query(new MemberBookMarkQueryRequest(id)));
    }
}
