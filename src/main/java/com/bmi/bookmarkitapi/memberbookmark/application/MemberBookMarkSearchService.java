package com.bmi.bookmarkitapi.memberbookmark.application;

import com.bmi.bookmarkitapi.bookmark.domain.model.BookMark;
import com.bmi.bookmarkitapi.common.exception.NotFoundException;
import com.bmi.bookmarkitapi.memberbookmark.application.model.BookMarkSearchDto;
import com.bmi.bookmarkitapi.memberbookmark.application.model.BookMarkSearchRequest;
import com.bmi.bookmarkitapi.memberbookmark.application.model.MemberBookMarkSearchRequest;
import com.bmi.bookmarkitapi.memberbookmark.domain.model.MemberBookMark;
import com.bmi.bookmarkitapi.memberbookmark.domain.service.MemberBookMarkQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberBookMarkSearchService {
    private final MemberBookMarkQueryService queryService;
    private final IBookMarkSearchService searchService;
    private final NotFoundException notFoundException;

    public Page<BookMarkSearchDto> search(MemberBookMarkSearchRequest searchRequest, Pageable pageable){
        List<MemberBookMark> memberBookMarkList = queryService.queryByMember(searchRequest.getMemberId());
        List<MemberBookMark> titleContainsMemberBookMarkList
                = queryService.queryByTitleContains(searchRequest.getMemberId(),searchRequest.getSearchText());
        List<Long> bookMarkIdList = memberBookMarkList
                .stream()
                .map(MemberBookMark::getMemberId)
                .collect(Collectors.toList());
        List<Long> titleContainsBookMarkIdList = titleContainsMemberBookMarkList
                .stream()
                .map(MemberBookMark::getMemberId)
                .collect(Collectors.toList());

        BookMarkSearchRequest request = new BookMarkSearchRequest(
                bookMarkIdList,
                titleContainsBookMarkIdList,
                searchRequest.getSearchText(),
                pageable);

        List<BookMark> searchResult = searchService.search(request);

        List<BookMarkSearchDto> responseResult = new ArrayList<>();

        searchResult.forEach(bookMark -> {
            Optional<MemberBookMark> memberBookMark = memberBookMarkList.stream()
                    .filter(mbm -> Objects.equals(mbm.getBookmarkId(), bookMark.getId()))
                    .findFirst();
            memberBookMark.ifPresent(mbm -> {
                BookMarkSearchDto bookMarkSearchDto = new BookMarkSearchDto(
                        mbm.getId(),
                        bookMark.getCategory(),
                        mbm.getTitle(),
                        bookMark.getLink(),
                        bookMark.getContent());
                responseResult.add(bookMarkSearchDto);
            });
        });

        return new PageImpl<>(responseResult,pageable,responseResult.size());
    }
}
