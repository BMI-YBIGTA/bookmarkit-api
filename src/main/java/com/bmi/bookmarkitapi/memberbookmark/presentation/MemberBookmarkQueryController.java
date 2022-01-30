package com.bmi.bookmarkitapi.memberbookmark.presentation;

import com.bmi.bookmarkitapi.common.BaseQueryController;
import com.bmi.bookmarkitapi.common.dto.Response;
import com.bmi.bookmarkitapi.common.security.JwtTokenProvider;
import com.bmi.bookmarkitapi.memberbookmark.application.MemberBookmarkCategoryQueryService;
import com.bmi.bookmarkitapi.memberbookmark.application.MemberBookmarkRecentQueryService;
import com.bmi.bookmarkitapi.memberbookmark.application.MemberBookmarkSearchService;
import com.bmi.bookmarkitapi.memberbookmark.application.model.BookmarkQueryDto;
import com.bmi.bookmarkitapi.memberbookmark.application.model.BookmarkSearchDto;
import com.bmi.bookmarkitapi.memberbookmark.application.model.MemberBookmarkQueryRequest;
import com.bmi.bookmarkitapi.memberbookmark.application.model.MemberBookmarkSearchRequest;
import com.bmi.bookmarkitapi.memberbookmark.domain.model.MemberBookmark;
import com.bmi.bookmarkitapi.memberbookmark.domain.service.MemberBookmarkQueryService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/memberbookmark")
public class MemberBookmarkQueryController extends BaseQueryController<MemberBookmark> {
    private final MemberBookmarkSearchService searchService;
    private final MemberBookmarkCategoryQueryService categoryQueryService;
    private final MemberBookmarkRecentQueryService recentQueryService;
    private final JwtTokenProvider jwtTokenProvider;

    public MemberBookmarkQueryController(
            MemberBookmarkQueryService queryService,
            MemberBookmarkSearchService searchService,
            MemberBookmarkCategoryQueryService categoryQueryService,
            MemberBookmarkRecentQueryService recentQueryService,
            JwtTokenProvider jwtTokenProvider
    ) {
        super(queryService);
        this.searchService = searchService;
        this.categoryQueryService = categoryQueryService;
        this.recentQueryService = recentQueryService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @GetMapping("/search")
    public Response.Page<BookmarkSearchDto> search(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "100") int size,
            @RequestParam(name = "query") String searchText,
            HttpServletRequest httpServletRequest
    ) {
        Long id = jwtTokenProvider.getMemberId(jwtTokenProvider.extractToken(httpServletRequest));

        Page<BookmarkSearchDto> tempPage = searchService.search(
                new MemberBookmarkSearchRequest(id, searchText), PageRequest.of(page, size)
        );

        return new Response.Page<>(tempPage.getContent(), tempPage.getTotalPages());
    }

    @GetMapping("/query")
    public Response.Item<Map<String, Map<String, List<BookmarkQueryDto>>>> query(
            @RequestParam(name = "category", defaultValue = "") String category,
            HttpServletRequest httpServletRequest
    ) {
        Long id = jwtTokenProvider.getMemberId(jwtTokenProvider.extractToken(httpServletRequest));

        return new Response.Item<>(categoryQueryService.query(new MemberBookmarkQueryRequest(id, category)));
    }

    @GetMapping("/recent")
    public Response.Item<Map<String,List<BookmarkQueryDto>>> query(
            HttpServletRequest httpServletRequest
    ) {
        Long id = jwtTokenProvider.getMemberId(jwtTokenProvider.extractToken(httpServletRequest));

        return new Response.Item<>(recentQueryService.query(new MemberBookmarkQueryRequest(id)));
    }
}
