package com.bmi.bookmarkitapi.memberbookmark.application;

import com.bmi.bookmarkitapi.memberbookmark.application.model.BookMarkCategoryQueryRequest;
import com.bmi.bookmarkitapi.memberbookmark.application.model.BookMarkQueryDto;
import com.bmi.bookmarkitapi.memberbookmark.application.model.MemberBookMarkQueryRequest;
import com.bmi.bookmarkitapi.memberbookmark.domain.exception.MemberBookMarkNotFoundException;
import com.bmi.bookmarkitapi.memberbookmark.domain.model.MemberBookMark;
import com.bmi.bookmarkitapi.memberbookmark.domain.service.MemberBookMarkQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberBookMarkCategoryQueryService {
    private final MemberBookMarkQueryService queryService;
    private final IBookMarkListQueryService listQueryService;

    public Map<String, Map<String, List<BookMarkQueryDto>>> query(MemberBookMarkQueryRequest queryRequest){
        List<MemberBookMark> memberBookMarkList = queryService.queryByMember(queryRequest.getMemberId());
        List<Long> bookMarkIdList = memberBookMarkList.stream()
                .map(MemberBookMark::getBookmarkId)
                .collect(Collectors.toList());

        List<BookMarkQueryDto> bookMarkList = listQueryService.query(new BookMarkCategoryQueryRequest(bookMarkIdList,
                        queryRequest.getMainCategory()))
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

        Map<String, Map<String, List<BookMarkQueryDto>>> map = new HashMap<>();

        Map<String, List<BookMarkQueryDto>> groupedByMainCategory = bookMarkList.stream()
                .collect(Collectors.groupingBy(BookMarkQueryDto::getMainCategory));

        for (Map.Entry<String, List<BookMarkQueryDto>> entry : groupedByMainCategory.entrySet()) {
            Map<String, List<BookMarkQueryDto>> groupedBySubCategory = entry.getValue()
                    .stream()
                    .collect(Collectors.groupingBy(BookMarkQueryDto::getSubCategory));

            map.put(entry.getKey(), groupedBySubCategory);
        }

        return map;
    }
}
