package com.bmi.bookmarkitapi.memberbookmark.application;

import com.bmi.bookmarkitapi.bookmark.domain.model.Bookmark;
import com.bmi.bookmarkitapi.memberbookmark.application.model.BookmarkSearchRequest;

import java.util.List;

public interface IBookmarkSearchService {
    List<Bookmark> search(BookmarkSearchRequest request);
}
