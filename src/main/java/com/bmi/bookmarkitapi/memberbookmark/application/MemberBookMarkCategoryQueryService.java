package com.bmi.bookmarkitapi.memberbookmark.application;

import com.bmi.bookmarkitapi.bookmark.domain.model.BookMark;
import com.bmi.bookmarkitapi.memberbookmark.application.model.BookMarkQueryDto;
import com.bmi.bookmarkitapi.memberbookmark.application.model.BookMarkQueryRequest;
import com.bmi.bookmarkitapi.memberbookmark.application.model.BookMarkSearchDto;
import com.bmi.bookmarkitapi.memberbookmark.application.model.MemberBookMarkQueryRequest;
import com.bmi.bookmarkitapi.memberbookmark.domain.model.MemberBookMark;
import com.bmi.bookmarkitapi.memberbookmark.domain.service.MemberBookMarkQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberBookMarkCategoryQueryService {
    private final MemberBookMarkQueryService queryService;
    private final IBookMarkCategoryQueryService categoryQueryService;

    public List<BookMarkQueryDto> query(MemberBookMarkQueryRequest queryRequest){
        List<MemberBookMark> memberBookMarkList = queryService.queryByMember(queryRequest.getMemberId());
        List<Long> bookMarkIdList = memberBookMarkList
                .stream()
                .map(MemberBookMark::getMemberId)
                .collect(Collectors.toList());

        BookMarkQueryRequest request = new BookMarkQueryRequest(
                bookMarkIdList,
                queryRequest.getCategory());

        List<BookMark> queryResult = categoryQueryService.query(request);


        List<BookMarkQueryDto> responseResult = new ArrayList<>();

        queryResult.forEach(bookMark -> {
            Optional<MemberBookMark> memberBookMark = memberBookMarkList.stream()
                    .filter(mbm -> Objects.equals(mbm.getBookmarkId(), bookMark.getId()))
                    .findFirst();
            memberBookMark.ifPresent(mbm -> {
                BookMarkQueryDto bookMarkQueryDto = new BookMarkQueryDto(
                        mbm.getId(),
                        bookMark.getCategory(),
                        mbm.getTitle(),
                        bookMark.getLink());
                responseResult.add(bookMarkQueryDto);
            });
        });

        return responseResult;
    }
}
