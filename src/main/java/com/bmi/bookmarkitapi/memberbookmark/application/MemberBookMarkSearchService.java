package com.bmi.bookmarkitapi.memberbookmark.application;

import com.bmi.bookmarkitapi.memberbookmark.application.model.BookMarkSearchDto;
import com.bmi.bookmarkitapi.memberbookmark.application.model.BookMarkSearchRequest;
import com.bmi.bookmarkitapi.memberbookmark.application.model.MemberBookMarkSearchRequest;
import com.bmi.bookmarkitapi.memberbookmark.domain.exception.MemberBookMarkNotFoundException;
import com.bmi.bookmarkitapi.memberbookmark.domain.model.MemberBookMark;
import com.bmi.bookmarkitapi.memberbookmark.domain.service.MemberBookMarkQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberBookMarkSearchService {
    private final MemberBookMarkQueryService queryService;
    private final IBookMarkSearchService searchService;

    public Page<BookMarkSearchDto> search(MemberBookMarkSearchRequest searchRequest, Pageable pageable){
        List<MemberBookMark> memberBookMarkList = queryService.queryByMember(searchRequest.getMemberId());
        List<MemberBookMark> titleContainsMemberBookMarkList = queryService.queryByTitleContains(
                searchRequest.getMemberId(), searchRequest.getSearchText()
        );
        List<Long> bookMarkIdList = memberBookMarkList.stream()
                .map(MemberBookMark::getBookmarkId)
                .collect(Collectors.toList());
        List<Long> titleContainsBookMarkIdList = titleContainsMemberBookMarkList.stream()
                .map(MemberBookMark::getMemberId)
                .collect(Collectors.toList());

        BookMarkSearchRequest request = new BookMarkSearchRequest(
                bookMarkIdList,
                titleContainsBookMarkIdList,
                searchRequest.getSearchText(),
                pageable
        );

        List<BookMarkSearchDto> bookMarkList = searchService.search(request)
                .stream()
                .map(bookMark -> {
                    MemberBookMark memberBookMark = memberBookMarkList.stream()
                            .filter(memberBookmark -> memberBookmark.getBookmarkId().equals(bookMark.getId()))
                            .findFirst()
                            .orElseThrow(MemberBookMarkNotFoundException::new);

                    return new BookMarkSearchDto(
                            bookMark.getMainCategory(),
                            bookMark.getSubCategory(),
                            memberBookMark.getTitle(),
                            bookMark.getLink(),
                            bookMark.summarizeContent(searchRequest.searchText),
                            bookMark.getStatus()
                    );
                })
                .collect(Collectors.toList());

        return new PageImpl<>(bookMarkList, pageable, bookMarkList.size());
    }
}
