package com.bmi.bookmarkitapi.bookmark.domain.service;

import com.bmi.bookmarkitapi.bookmark.domain.exception.BookMarkNotFoundException;
import com.bmi.bookmarkitapi.bookmark.domain.model.BookMark;
import com.bmi.bookmarkitapi.bookmark.domain.repository.BookMarkCustomRepository;
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
    private final BookMarkCustomRepository customRepository;

    public BookMarkQueryService(BookMarkRepository bookMarkRepository ,
                                BookMarkCustomRepository customRepository) {
        super(bookMarkRepository, new BookMarkNotFoundException());
        this.bookMarkRepository = bookMarkRepository;
        this.customRepository = customRepository;
    }

    public BookMark query(String link) {
        return bookMarkRepository.findByLink(link).orElseThrow(() -> new BookMarkNotFoundException());
    }
}
