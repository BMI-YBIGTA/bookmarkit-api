package com.bmi.bookmarkitapi.bookmark.domain.service;

import com.bmi.bookmarkitapi.bookmark.domain.model.BookMark;
import com.bmi.bookmarkitapi.bookmark.domain.repository.BookMarkRepository;
import com.bmi.bookmarkitapi.common.BaseCommandService;
import org.springframework.stereotype.Service;

@Service
public class BookMarkCommandService extends BaseCommandService<BookMark> {

    public BookMarkCommandService(BookMarkRepository repository) {
        super(repository);
    }

    public BookMark create(String header, String link, String content) {
        return this.save(new BookMark(header, link, content));
    }
}
