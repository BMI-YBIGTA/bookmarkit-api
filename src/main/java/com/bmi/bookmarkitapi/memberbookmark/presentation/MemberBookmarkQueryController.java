package com.bmi.bookmarkitapi.memberbookmark.presentation;

import com.bmi.bookmarkitapi.common.dto.Response;
import com.bmi.bookmarkitapi.common.security.JwtTokenProvider;
import com.bmi.bookmarkitapi.memberbookmark.application.MemberBookmarkListQueryService;
import com.bmi.bookmarkitapi.memberbookmark.application.MemberBookmarkSearchService;
import com.bmi.bookmarkitapi.memberbookmark.application.MemberBookmarkService;
import com.bmi.bookmarkitapi.memberbookmark.application.model.request.MemberBookmarkCategoryQueryRequest;
import com.bmi.bookmarkitapi.memberbookmark.application.model.request.MemberBookmarkRecentQueryRequest;
import com.bmi.bookmarkitapi.memberbookmark.application.model.request.MemberBookmarkSearchRequest;
import com.bmi.bookmarkitapi.memberbookmark.application.model.response.MemberBookmarkCategoryQueryResponse;
import com.bmi.bookmarkitapi.memberbookmark.application.model.response.MemberBookmarkRecentQueryResponse;
import com.bmi.bookmarkitapi.memberbookmark.application.model.response.MemberBookmarkResponse;
import com.bmi.bookmarkitapi.memberbookmark.application.model.response.MemberBookmarkSearchResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@RequestMapping("/api/memberbookmark")
@RestController
public class MemberBookmarkQueryController {

    private final MemberBookmarkService memberBookmarkService;
    private final MemberBookmarkSearchService memberBookmarkSearchService;
    private final MemberBookmarkListQueryService memberBookmarkListQueryService;
    private final JwtTokenProvider jwtTokenProvider;

    @GetMapping
    public Response.ItemList<MemberBookmarkResponse> getMemberBookmarks() {
        return new Response.ItemList<>(memberBookmarkService.getMemberBookmarks());
    }

    @GetMapping("/{id}")
    public Response.Item<MemberBookmarkResponse> getMemberBookmark(@PathVariable Long id) {
        return new Response.Item<>(memberBookmarkService.getMemberBookmark(id));
    }

    @GetMapping("/page")
    public Response.ItemPage<MemberBookmarkResponse> getMemberBookmarkPage(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "30") int size
    ) {
        return new Response.ItemPage<>(memberBookmarkService.getMemberBookmarkPage(page, size));
    }

    @GetMapping("/search")
    public Response.ItemList<MemberBookmarkSearchResponse> search(
            @RequestParam(name = "bookmarkId") Long bookmarkId,
            @RequestParam(name = "title", defaultValue = "") String searchText,
            @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
            HttpServletRequest httpServletRequest
    ) {
        Long memberId = jwtTokenProvider.getMemberId(jwtTokenProvider.extractToken(httpServletRequest));
        return new Response.ItemList<>(
                memberBookmarkSearchService.search(new MemberBookmarkSearchRequest(memberId, bookmarkId, searchText, pageSize))
        );
    }

    @GetMapping("/category")
    public Response.ItemList<MemberBookmarkCategoryQueryResponse> getBookmarksByCategory(
            @RequestParam(name = "category", defaultValue = "") String category,
            HttpServletRequest httpServletRequest
    ) {
        Long memberId = jwtTokenProvider.getMemberId(jwtTokenProvider.extractToken(httpServletRequest));
        return new Response.ItemList<>(
                memberBookmarkListQueryService.getBookmarksByCategory(new MemberBookmarkCategoryQueryRequest(memberId, category))
        );
    }

    @GetMapping("/recent")
    public Response.ItemList<MemberBookmarkRecentQueryResponse> getRecentBookmarks(HttpServletRequest httpServletRequest) {
        Long memberId = jwtTokenProvider.getMemberId(jwtTokenProvider.extractToken(httpServletRequest));
        return new Response.ItemList<>(
                memberBookmarkListQueryService.getRecentBookmarks(new MemberBookmarkRecentQueryRequest(memberId))
        );
    }
}
