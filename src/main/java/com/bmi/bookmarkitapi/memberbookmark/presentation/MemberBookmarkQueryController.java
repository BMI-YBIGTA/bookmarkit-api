package com.bmi.bookmarkitapi.memberbookmark.presentation;

import com.bmi.bookmarkitapi.common.dto.Response;
import com.bmi.bookmarkitapi.common.security.JwtTokenProvider;
import com.bmi.bookmarkitapi.memberbookmark.application.MemberBookmarkCategoryQueryService;
import com.bmi.bookmarkitapi.memberbookmark.application.MemberBookmarkRecentQueryService;
import com.bmi.bookmarkitapi.memberbookmark.application.MemberBookmarkSearchService;
import com.bmi.bookmarkitapi.memberbookmark.application.MemberBookmarkService;
import com.bmi.bookmarkitapi.memberbookmark.application.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/memberbookmark")
public class MemberBookmarkQueryController {

    private final MemberBookmarkService memberBookmarkService;
    private final MemberBookmarkSearchService searchService;
    private final MemberBookmarkCategoryQueryService categoryQueryService;
    private final MemberBookmarkRecentQueryService recentQueryService;
    private final JwtTokenProvider jwtTokenProvider;

    @GetMapping
    public Response.ItemList<MemberBookmarkDto.Response> findAll() {
        return new Response.ItemList<>(memberBookmarkService.findAll());
    }

    @GetMapping("/{id}")
    public Response.Item<MemberBookmarkDto.Response> findById(@PathVariable Long id) {
        return new Response.Item<>(memberBookmarkService.findById(id));
    }

    @GetMapping("/page")
    public Response.ItemPage<MemberBookmarkDto.Response> findByPage(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "30") int size
    ) {
        return new Response.ItemPage<>(memberBookmarkService.findByPage(page, size));
    }

    @GetMapping("/search")
    public Response.ItemPage<BookmarkSearchDto> search(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "100") int size,
            @RequestParam(name = "query") String searchText,
            HttpServletRequest httpServletRequest
    ) {
        Long id = jwtTokenProvider.getMemberId(jwtTokenProvider.extractToken(httpServletRequest));

        Page<BookmarkSearchDto> tempPage = searchService.search(
                new MemberBookmarkSearchRequest(id, searchText), PageRequest.of(page, size)
        );

        return new Response.ItemPage<>(tempPage);
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
