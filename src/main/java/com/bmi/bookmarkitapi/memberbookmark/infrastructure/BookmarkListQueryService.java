package com.bmi.bookmarkitapi.memberbookmark.infrastructure;

import com.bmi.bookmarkitapi.bookmark.domain.model.Bookmark;
import com.bmi.bookmarkitapi.bookmark.domain.service.BookmarkQueryService;
import com.bmi.bookmarkitapi.memberbookmark.application.IBookmarkListQueryService;
import com.bmi.bookmarkitapi.memberbookmark.application.model.BookmarkCategoryQueryRequest;
import com.bmi.bookmarkitapi.memberbookmark.application.model.BookmarkRecentQueryRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BookmarkListQueryService implements IBookmarkListQueryService {

    private final BookmarkQueryService queryService;

    @Override
    public List<Bookmark> query(BookmarkCategoryQueryRequest request) {
        return queryService.findByCategory(request.bookmarkIdList, request.mainCategory);
    }

    @Override
    public List<Bookmark> query(BookmarkRecentQueryRequest request) {
        return  queryService.findRecent20Bookmarks(request.bookmarkIdList);
    }
}
