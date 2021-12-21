package com.bmi.bookmarkitapi.bookmark.application;

import com.bmi.bookmarkitapi.bookmark.domain.model.BookMark;
import com.bmi.bookmarkitapi.bookmark.domain.service.BookMarkCommandService;
import com.bmi.bookmarkitapi.bookmark.domain.service.BookMarkQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookMarkCategorySettingService {
    private final BookMarkQueryService queryService;
    private final BookMarkCommandService commandService;

    public void set(Long bookMarkId, String category) {
        BookMark bookMark = queryService.query(bookMarkId);
        bookMark.setCategory(category);
        commandService.save(bookMark);
    }
}
