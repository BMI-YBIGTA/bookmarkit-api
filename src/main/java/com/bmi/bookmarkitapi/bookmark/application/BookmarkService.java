package com.bmi.bookmarkitapi.bookmark.application;

import com.bmi.bookmarkitapi.bookmark.application.model.request.BookmarkRegisterRequest;
import com.bmi.bookmarkitapi.bookmark.application.model.response.BookmarkResponse;
import com.bmi.bookmarkitapi.bookmark.domain.model.Bookmark;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BookmarkService {

    List<BookmarkResponse> findAll();

    BookmarkResponse findById(Long id);

    Page<BookmarkResponse> findByPage(int page, int size);

    Bookmark register(BookmarkRegisterRequest request);
}
