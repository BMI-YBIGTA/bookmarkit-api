package com.bmi.bookmarkitapi.memberbookmark.application;

import com.bmi.bookmarkitapi.memberbookmark.application.model.BookmarkCategoryQueryRequest;
import com.bmi.bookmarkitapi.memberbookmark.application.model.BookmarkQueryDto;
import com.bmi.bookmarkitapi.memberbookmark.application.model.MemberBookmarkQueryRequest;
import com.bmi.bookmarkitapi.memberbookmark.domain.exception.MemberBookmarkNotFoundException;
import com.bmi.bookmarkitapi.memberbookmark.domain.model.MemberBookmark;
import com.bmi.bookmarkitapi.memberbookmark.domain.service.MemberBookmarkQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberBookmarkCategoryQueryService {
    private final MemberBookmarkQueryService queryService;
    private final IBookmarkListQueryService listQueryService;

    public Map<String, Map<String, List<BookmarkQueryDto>>> query(MemberBookmarkQueryRequest queryRequest){
        List<MemberBookmark> memberBookmarkList = queryService.queryByMember(queryRequest.getMemberId());
        List<Long> bookmarkIdList = memberBookmarkList.stream()
                .map(MemberBookmark::getBookmarkId)
                .collect(Collectors.toList());

        List<BookmarkQueryDto> bookmarkList = listQueryService.query(new BookmarkCategoryQueryRequest(bookmarkIdList,
                        queryRequest.getMainCategory()))
                .stream()
                .map(bookmark -> {
                    MemberBookmark memberBookmark = memberBookmarkList.stream()
                            .filter(mb -> mb.getBookmarkId().equals(bookmark.getId()))
                            .findFirst()
                            .orElseThrow(MemberBookmarkNotFoundException::new);

                    return new BookmarkQueryDto(
                            memberBookmark.getBookmarkId(),
                            bookmark.getMainCategory(),
                            bookmark.getSubCategory(),
                            memberBookmark.getTitle(),
                            bookmark.getLink(),
                            bookmark.dateTimeToString(),
                            bookmark.getStatus()
                    );
                })
                .collect(Collectors.toList());

        Map<String, Map<String, List<BookmarkQueryDto>>> map = new HashMap<>();

        Map<String, List<BookmarkQueryDto>> groupedByMainCategory = bookmarkList.stream()
                .collect(Collectors.groupingBy(BookmarkQueryDto::getMainCategory));

        for (Map.Entry<String, List<BookmarkQueryDto>> entry : groupedByMainCategory.entrySet()) {
            Map<String, List<BookmarkQueryDto>> groupedBySubCategory = entry.getValue()
                    .stream()
                    .collect(Collectors.groupingBy(BookmarkQueryDto::getSubCategory));

            map.put(entry.getKey(), groupedBySubCategory);
        }

        return map;
    }
}
