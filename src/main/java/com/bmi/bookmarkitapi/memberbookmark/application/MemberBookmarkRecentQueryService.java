package com.bmi.bookmarkitapi.memberbookmark.application;

import com.bmi.bookmarkitapi.common.exception.ResourceNotFoundException;
import com.bmi.bookmarkitapi.common.util.DateTimeUtils;
import com.bmi.bookmarkitapi.memberbookmark.application.model.BookmarkQueryDto;
import com.bmi.bookmarkitapi.memberbookmark.application.model.BookmarkRecentQueryRequest;
import com.bmi.bookmarkitapi.memberbookmark.application.model.MemberBookmarkQueryRequest;
import com.bmi.bookmarkitapi.memberbookmark.domain.model.MemberBookmark;
import com.bmi.bookmarkitapi.memberbookmark.domain.service.MemberBookmarkQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberBookmarkRecentQueryService {
    private final MemberBookmarkQueryService queryService;
    private final IBookmarkListQueryService listQueryService;

    public Map<String, List<BookmarkQueryDto>> query(MemberBookmarkQueryRequest queryRequest){
        List<MemberBookmark> memberBookmarkList = queryService.queryByMember(queryRequest.getMemberId());
        List<Long> bookmarkIdList = memberBookmarkList.stream()
                .map(MemberBookmark::getBookmarkId)
                .collect(Collectors.toList());

        List<BookmarkQueryDto> bookmarkList = listQueryService.query(new BookmarkRecentQueryRequest(bookmarkIdList))
                .stream()
                .map(bookmark -> {
                    MemberBookmark memberBookmark = memberBookmarkList.stream()
                            .filter(mb -> mb.getBookmarkId().equals(bookmark.getId()))
                            .findFirst()
                            .orElseThrow(ResourceNotFoundException::new);

                    return new BookmarkQueryDto(
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

        return bookmarkList.stream()
                .collect(Collectors.groupingBy(BookmarkQueryDto::getCreatedDate));
    }
}
