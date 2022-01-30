package com.bmi.bookmarkitapi.bookmark.domain.service;

import com.bmi.bookmarkitapi.bookmark.domain.model.Bookmark;
import com.bmi.bookmarkitapi.bookmark.domain.repository.BookmarkRepository;
import com.bmi.bookmarkitapi.common.BaseCommandService;
import org.springframework.stereotype.Service;

@Service
public class BookmarkCommandService extends BaseCommandService<Bookmark> {

    public BookmarkCommandService(BookmarkRepository repository) {
        super(repository);
    }

    public Bookmark create(String link) {
        return this.save(new Bookmark(link));
    }
}
