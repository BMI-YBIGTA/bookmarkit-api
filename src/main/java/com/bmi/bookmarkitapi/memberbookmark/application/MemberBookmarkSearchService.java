package com.bmi.bookmarkitapi.memberbookmark.application;

import com.bmi.bookmarkitapi.memberbookmark.application.model.BookmarkSearchDto;
import com.bmi.bookmarkitapi.memberbookmark.application.model.BookmarkSearchRequest;
import com.bmi.bookmarkitapi.memberbookmark.application.model.MemberBookmarkSearchRequest;
import com.bmi.bookmarkitapi.memberbookmark.domain.exception.MemberBookmarkNotFoundException;
import com.bmi.bookmarkitapi.memberbookmark.domain.model.MemberBookmark;
import com.bmi.bookmarkitapi.memberbookmark.domain.service.MemberBookmarkQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberBookmarkSearchService {
    private final MemberBookmarkQueryService queryService;
    private final IBookmarkSearchService searchService;

    public Page<BookmarkSearchDto> search(MemberBookmarkSearchRequest searchRequest, Pageable pageable){
        List<MemberBookmark> memberBookmarkList = queryService.queryByMember(searchRequest.getMemberId());
        List<MemberBookmark> titleContainsMemberBookmarkList = queryService.queryByTitleContains(
                searchRequest.getMemberId(), searchRequest.getSearchText()
        );
        List<Long> bookmarkIdList = memberBookmarkList.stream()
                .map(MemberBookmark::getBookmarkId)
                .collect(Collectors.toList());
        List<Long> titleContainsBookmarkIdList = titleContainsMemberBookmarkList.stream()
                .map(MemberBookmark::getMemberId)
                .collect(Collectors.toList());

        BookmarkSearchRequest request = new BookmarkSearchRequest(
                bookmarkIdList,
                titleContainsBookmarkIdList,
                searchRequest.getSearchText(),
                pageable
        );

        List<BookmarkSearchDto> bookmarkList = searchService.search(request)
                .stream()
                .map(bookmark -> {
                    MemberBookmark memberBookmark = memberBookmarkList.stream()
                            .filter(mb -> mb.getBookmarkId().equals(bookmark.getId()))
                            .findFirst()
                            .orElseThrow(MemberBookmarkNotFoundException::new);

                    return new BookmarkSearchDto(
                            bookmark.getMainCategory(),
                            bookmark.getSubCategory(),
                            memberBookmark.getTitle(),
                            bookmark.getLink(),
                            bookmark.summarizeContent(searchRequest.searchText),
                            bookmark.getStatus()
                    );
                })
                .collect(Collectors.toList());

        return new PageImpl<>(bookmarkList, pageable, bookmarkList.size());
    }
}
