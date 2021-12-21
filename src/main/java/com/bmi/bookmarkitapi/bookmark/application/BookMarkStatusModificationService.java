package com.bmi.bookmarkitapi.bookmark.application;

import com.bmi.bookmarkitapi.bookmark.domain.model.BookMark;
import com.bmi.bookmarkitapi.bookmark.domain.service.BookMarkCommandService;
import com.bmi.bookmarkitapi.bookmark.domain.service.BookMarkQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BookMarkStatusModificationService {
    private final BookMarkQueryService queryService;
    private final BookMarkCommandService commandService;

    public void request(Long bookMarkId) {
        BookMark bookMark = queryService.query(bookMarkId);
        bookMark.request();
        commandService.save(bookMark);
    }

    public void complete(Long bookMarkId) {
        BookMark bookMark = queryService.query(bookMarkId);
        bookMark.complete();
        commandService.save(bookMark);
    }
}
