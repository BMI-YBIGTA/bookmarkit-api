package com.bmi.bookmarkitapi.memberbookmark.application;

import com.bmi.bookmarkitapi.memberbookmark.application.model.BookMarkQueryDto;
import com.bmi.bookmarkitapi.memberbookmark.application.model.BookMarkRecentQueryRequest;
import com.bmi.bookmarkitapi.memberbookmark.application.model.MemberBookMarkQueryRequest;
import com.bmi.bookmarkitapi.memberbookmark.domain.exception.MemberBookMarkNotFoundException;
import com.bmi.bookmarkitapi.memberbookmark.domain.model.MemberBookMark;
import com.bmi.bookmarkitapi.memberbookmark.domain.service.MemberBookMarkQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberBookMarkRecentQueryService {
    private final MemberBookMarkQueryService queryService;
    private final IBookMarkListQueryService listQueryService;

    public Map<String, List<BookMarkQueryDto>> query(MemberBookMarkQueryRequest queryRequest){
        List<MemberBookMark> memberBookMarkList = queryService.queryByMember(queryRequest.getMemberId());
        List<Long> bookMarkIdList = memberBookMarkList.stream()
                .map(MemberBookMark::getBookmarkId)
                .collect(Collectors.toList());

        List<BookMarkQueryDto> bookMarkList = listQueryService.query(new BookMarkRecentQueryRequest(bookMarkIdList))
                .stream()
                .map(bookMark -> {
                    MemberBookMark memberBookMark = memberBookMarkList.stream()
                            .filter(memberBookmark -> memberBookmark.getBookmarkId().equals(bookMark.getId()))
                            .findFirst()
                            .orElseThrow(MemberBookMarkNotFoundException::new);

                    return new BookMarkQueryDto(
                            memberBookMark.getBookmarkId(),
                            bookMark.getMainCategory(),
                            bookMark.getSubCategory(),
                            memberBookMark.getTitle(),
                            bookMark.getLink(),
                            bookMark.dateTimeToString(),
                            bookMark.getStatus()
                    );
                })
                .collect(Collectors.toList());

        return bookMarkList.stream()
                .collect(Collectors.groupingBy(BookMarkQueryDto::getCreatedDate));
    }
}
