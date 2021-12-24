package com.bmi.bookmarkitapi.memberbookmark.application;

import com.bmi.bookmarkitapi.bookmark.domain.model.BookMark;
import com.bmi.bookmarkitapi.memberbookmark.application.model.BookMarkCategoryQueryRequest;
import com.bmi.bookmarkitapi.memberbookmark.application.model.BookMarkQueryDto;
import com.bmi.bookmarkitapi.memberbookmark.application.model.BookMarkRecentQueryRequest;
import com.bmi.bookmarkitapi.memberbookmark.application.model.MemberBookMarkQueryRequest;
import com.bmi.bookmarkitapi.memberbookmark.domain.model.MemberBookMark;
import com.bmi.bookmarkitapi.memberbookmark.domain.service.MemberBookMarkQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberBookMarkRecentQueryService {
    private final MemberBookMarkQueryService queryService;
    private final IBookMarkListQueryService listQueryService;

    public Map<String, List<BookMarkQueryDto>> query(MemberBookMarkQueryRequest queryRequest){
        List<MemberBookMark> memberBookMarkList = queryService.queryByMember(queryRequest.getMemberId());
        List<Long> bookMarkIdList = memberBookMarkList
                .stream()
                .map(MemberBookMark::getBookmarkId)
                .collect(Collectors.toList());

        BookMarkRecentQueryRequest request = new BookMarkRecentQueryRequest(bookMarkIdList);

        List<BookMark> queryResult = listQueryService.query(request);

        List<BookMarkQueryDto> responseList = new ArrayList<>();

        queryResult.forEach(bookMark -> {
            Optional<MemberBookMark> memberBookMark = memberBookMarkList.stream()
                    .filter(mbm -> Objects.equals(mbm.getBookmarkId(), bookMark.getId()))
                    .findFirst();
            memberBookMark.ifPresent(mbm -> {
                BookMarkQueryDto bookMarkQueryDto = new BookMarkQueryDto(
                        bookMark.getMainCategory(),
                        bookMark.getSubCategory(),
                        mbm.getTitle(),
                        bookMark.getLink(),
                        bookMark.createDateToString(),
                        bookMark.getStatus());
                responseList.add(bookMarkQueryDto);
            });
        });

        Map<String, List<BookMarkQueryDto>> map = responseList.stream()
                .collect(Collectors.groupingBy(BookMarkQueryDto::getCreatedDate));



        return map;
    }
}
