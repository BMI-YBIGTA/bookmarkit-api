package com.bmi.bookmarkitapi.memberbookmark.application;

import com.bmi.bookmarkitapi.bookmark.domain.model.Bookmark;
import com.bmi.bookmarkitapi.memberbookmark.application.model.BookmarkCategoryQueryRequest;
import com.bmi.bookmarkitapi.memberbookmark.application.model.BookmarkRecentQueryRequest;

import java.util.List;

public interface IBookmarkListQueryService {
    List<Bookmark> query(BookmarkCategoryQueryRequest request);
    List<Bookmark> query(BookmarkRecentQueryRequest request);
}
