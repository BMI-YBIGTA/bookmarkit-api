package com.bmi.bookmarkitapi.memberbookmark.application;

import com.bmi.bookmarkitapi.bookmark.domain.model.Bookmark;
import com.bmi.bookmarkitapi.common.exception.ResourceNotFoundException;
import com.bmi.bookmarkitapi.memberbookmark.application.model.request.MemberBookmarkSearchRequest;
import com.bmi.bookmarkitapi.memberbookmark.application.model.response.MemberBookmarkSearchResponse;
import com.bmi.bookmarkitapi.memberbookmark.domain.model.MemberBookmark;
import com.bmi.bookmarkitapi.memberbookmark.domain.service.MemberBookmarkQueryService;
import com.bmi.bookmarkitapi.memberbookmark.infrastructure.BookmarkSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MemberBookmarkSearchServiceImpl implements MemberBookmarkSearchService {

    private final MemberBookmarkQueryService memberBookmarkQueryService;
    private final BookmarkSearchService bookmarkSearchService;

    @Override
    public Page<MemberBookmarkSearchResponse> search(MemberBookmarkSearchRequest request, Pageable pageable) {
        List<MemberBookmark> memberBookmarks = memberBookmarkQueryService.findByMemberId(request.getMemberId());
        List<Long> bookmarkIds = memberBookmarks.stream()
                .map(MemberBookmark::getBookmarkId)
                .collect(Collectors.toList());

        List<Long> searchedBookmarkIds = memberBookmarkQueryService.search(request.getMemberId(), request.getSearchText())
                .stream()
                .map(MemberBookmark::getBookmarkId)
                .collect(Collectors.toList());

        List<MemberBookmarkSearchResponse> bookmarks = getBookmarkSearchResponses(
                bookmarkSearchService.search(bookmarkIds, searchedBookmarkIds, request.getSearchText(), pageable),
                memberBookmarks,
                request.getSearchText()
        );
        return new PageImpl<>(bookmarks, pageable, bookmarks.size());
    }

    private List<MemberBookmarkSearchResponse> getBookmarkSearchResponses(
            List<Bookmark> bookmarks, List<MemberBookmark> memberBookmarks, String searchText
    ) {
        return bookmarks.stream()
                .map(bookmark -> {
                    MemberBookmark memberBookmark = memberBookmarks.stream()
                            .filter(mb -> mb.getBookmarkId().equals(bookmark.getId()))
                            .findFirst()
                            .orElseThrow(() ->
                                    new ResourceNotFoundException("해당 Bookmark id를 가진 MemberBookmark가 존재하지 않습니다. bookmarkId="
                                            + bookmark.getId())
                            );

                    return new MemberBookmarkSearchResponse(
                            bookmark.getMainCategory(),
                            bookmark.getSubCategory(),
                            memberBookmark.getTitle(),
                            bookmark.getLink(),
                            bookmark.summarizeContent(searchText),
                            bookmark.getStatus()
                    );
                })
                .collect(Collectors.toList());
    }
}
