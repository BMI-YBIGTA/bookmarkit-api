package com.bmi.bookmarkitapi.memberbookmark.application;

import com.bmi.bookmarkitapi.bookmark.domain.model.Bookmark;
import com.bmi.bookmarkitapi.common.exception.ResourceNotFoundException;
import com.bmi.bookmarkitapi.common.util.DateTimeUtils;
import com.bmi.bookmarkitapi.memberbookmark.application.model.request.MemberBookmarkCategoryQueryRequest;
import com.bmi.bookmarkitapi.memberbookmark.application.model.request.MemberBookmarkRecentQueryRequest;
import com.bmi.bookmarkitapi.memberbookmark.application.model.response.BookmarkQueryResponse;
import com.bmi.bookmarkitapi.memberbookmark.application.model.response.MemberBookmarkCategoryQueryResponse;
import com.bmi.bookmarkitapi.memberbookmark.application.model.response.MemberBookmarkRecentQueryResponse;
import com.bmi.bookmarkitapi.memberbookmark.domain.model.MemberBookmark;
import com.bmi.bookmarkitapi.memberbookmark.domain.service.MemberBookmarkQueryService;
import com.bmi.bookmarkitapi.memberbookmark.infrastructure.BookmarkListQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MemberBookmarkListQueryServiceImpl implements MemberBookmarkListQueryService {

    private final MemberBookmarkQueryService memberBookmarkQueryService;
    private final BookmarkListQueryService bookmarkListQueryService;

    @Override
    public List<MemberBookmarkCategoryQueryResponse> getBookmarksByCategory(MemberBookmarkCategoryQueryRequest request) {
        List<MemberBookmark> memberBookmarks = memberBookmarkQueryService.findByMemberId(request.getMemberId());
        List<Long> bookmarkIds = memberBookmarks.stream()
                .map(MemberBookmark::getBookmarkId)
                .collect(Collectors.toList());

        Map<String, List<BookmarkQueryResponse>> groupedByMainCategory = getBookmarkQueryResponses(
                bookmarkListQueryService.getBookmarksByCategory(bookmarkIds, request.getMainCategory()),
                memberBookmarks
        )
                .stream()
                .collect(Collectors.groupingBy(BookmarkQueryResponse::getMainCategory));

        Map<String, Map<String, List<BookmarkQueryResponse>>> map = new HashMap<>();

        for (Map.Entry<String, List<BookmarkQueryResponse>> entry : groupedByMainCategory.entrySet()) {
            Map<String, List<BookmarkQueryResponse>> groupedBySubCategory = entry.getValue()
                    .stream()
                    .collect(Collectors.groupingBy(BookmarkQueryResponse::getSubCategory));
            map.put(entry.getKey(), groupedBySubCategory);
        }

        return map.entrySet()
                .stream()
                .map(mainEntry -> {
                    List<MemberBookmarkCategoryQueryResponse.SubCategory> subCategories = mainEntry.getValue()
                            .entrySet()
                            .stream()
                            .map(subEntry ->
                                    new MemberBookmarkCategoryQueryResponse.SubCategory(
                                            subEntry.getKey(),
                                            subEntry.getValue()
                                                    .stream()
                                                    .map(MemberBookmarkCategoryQueryResponse.SubCategory.MemberBookmarkDto::new)
                                                    .collect(Collectors.toList())
                                    )
                            )
                            .collect(Collectors.toList());
                    return new MemberBookmarkCategoryQueryResponse(mainEntry.getKey(), subCategories);
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<MemberBookmarkRecentQueryResponse> getRecentBookmarks(MemberBookmarkRecentQueryRequest request) {
        List<MemberBookmark> memberBookmarks = memberBookmarkQueryService.findByMemberId(request.getMemberId());
        List<Long> bookmarkIds = memberBookmarks.stream()
                .map(MemberBookmark::getBookmarkId)
                .collect(Collectors.toList());

        Map<String, List<BookmarkQueryResponse>> groupedByCreatedDate = getBookmarkQueryResponses(
                bookmarkListQueryService.getRecentBookmarks(bookmarkIds),
                memberBookmarks
        )
                .stream()
                .collect(Collectors.groupingBy(BookmarkQueryResponse::getCreatedDate));

        return groupedByCreatedDate.entrySet()
                .stream()
                .map(entry ->
                        new MemberBookmarkRecentQueryResponse(
                                entry.getKey(),
                                entry.getValue()
                                        .stream()
                                        .map(MemberBookmarkRecentQueryResponse.MemberBookmarkDto::new)
                                        .collect(Collectors.toList())
                        )
                )
                .collect(Collectors.toList());
    }

    private List<BookmarkQueryResponse> getBookmarkQueryResponses(List<Bookmark> bookmarks, List<MemberBookmark> memberBookmarks) {
        return bookmarks.stream()
                .map(bookmark -> {
                    MemberBookmark memberBookmark = memberBookmarks.stream()
                            .filter(mb -> mb.getBookmarkId().equals(bookmark.getId()))
                            .findFirst()
                            .orElseThrow(() ->
                                    new ResourceNotFoundException("해당 Bookmark id를 가진 MemberBookmark가 존재하지 않습니다. bookmarkId="
                                            + bookmark.getId())
                            );

                    return new BookmarkQueryResponse(
                            memberBookmark.getBookmarkId(),
                            bookmark.getMainCategory(),
                            bookmark.getSubCategory(),
                            memberBookmark.getTitle(),
                            bookmark.getLink(),
                            DateTimeUtils.toStringWithMonthAndDay(bookmark.getCreatedAt()),
                            bookmark.getStatus()
                    );
                })
                .collect(Collectors.toList());
    }
}
