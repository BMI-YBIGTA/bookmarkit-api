package com.bmi.bookmarkitapi.bookmark.domain.service;

import com.bmi.bookmarkitapi.bookmark.domain.exception.BookMarkNotFoundException;
import com.bmi.bookmarkitapi.bookmark.domain.model.BookMark;
import com.bmi.bookmarkitapi.bookmark.domain.repository.BookMarkRepository;
import com.bmi.bookmarkitapi.common.BaseQueryService;
import com.bmi.bookmarkitapi.common.BaseRepository;
import com.bmi.bookmarkitapi.common.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class BookMarkQueryService  extends BaseQueryService<BookMark> {

    private final BookMarkRepository bookMarkRepository;

    public BookMarkQueryService(BaseRepository<BookMark> repository,BookMarkRepository bookMarkRepository ) {
        super(repository, new BookMarkNotFoundException());
        this.bookMarkRepository = bookMarkRepository;
    }

    public BookMark query(String link) {
        return bookMarkRepository.findByLink(link).orElseThrow(() -> new BookMarkNotFoundException());
    }
}
