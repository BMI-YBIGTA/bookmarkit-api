package com.bmi.bookmarkitapi.bookmark.domain.service;

import com.bmi.bookmarkitapi.bookmark.domain.exception.BookMarkNotFoundException;
import com.bmi.bookmarkitapi.bookmark.domain.model.BookMark;
import com.bmi.bookmarkitapi.bookmark.domain.repository.BookMarkCustomRepository;
import com.bmi.bookmarkitapi.bookmark.domain.repository.BookMarkRepository;
import com.bmi.bookmarkitapi.common.BaseQueryService;
import com.bmi.bookmarkitapi.common.BaseRepository;
import com.bmi.bookmarkitapi.common.exception.NotFoundException;
import com.bmi.bookmarkitapi.memberbookmark.application.model.BookMarkQueryRequest;
import com.bmi.bookmarkitapi.memberbookmark.application.model.BookMarkSearchRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class BookMarkQueryService  extends BaseQueryService<BookMark> {

    private final BookMarkRepository bookMarkRepository;
    private final BookMarkCustomRepository customRepository;

    public BookMarkQueryService(
            BaseRepository<BookMark> repository,
            BookMarkRepository bookMarkRepository,
            BookMarkCustomRepository customRepository) {
        super(repository, new BookMarkNotFoundException());
        this.bookMarkRepository = bookMarkRepository;
        this.customRepository = customRepository;

    }

    public BookMark query(String link) {
        return bookMarkRepository.findByLink(link).orElseThrow(() -> new BookMarkNotFoundException());
    }

    public List<BookMark> query(BookMarkSearchRequest request){
        return customRepository.search(
                request.getBookMarkIdList(),
                request.getTitleContainsBookMarkIdList(),
                request.getSearchText(),
                request.getPageable()
        );
    }

    public List<BookMark> query(BookMarkQueryRequest request){
        List<BookMark> result;
        if(request.getCategory().isPresent()){
            return bookMarkRepository.findByIdInAndCategoryEquals(
                    request.getBookMarkIdList(),
                    request.getCategory().get());
        }
        else{
            return bookMarkRepository.findByIdIn(request.getBookMarkIdList());
        }

    }
}
