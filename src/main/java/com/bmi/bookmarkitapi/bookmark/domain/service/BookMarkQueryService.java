package com.bmi.bookmarkitapi.bookmark.domain.service;

import com.bmi.bookmarkitapi.bookmark.domain.exception.BookMarkNotFoundException;
import com.bmi.bookmarkitapi.bookmark.domain.model.BookMark;
import com.bmi.bookmarkitapi.bookmark.domain.repository.BookMarkCustomRepository;
import com.bmi.bookmarkitapi.bookmark.domain.repository.BookMarkRepository;
import com.bmi.bookmarkitapi.common.BaseQueryService;
import com.bmi.bookmarkitapi.memberbookmark.application.model.BookMarkCategoryQueryRequest;
import com.bmi.bookmarkitapi.memberbookmark.application.model.BookMarkRecentQueryRequest;
import com.bmi.bookmarkitapi.memberbookmark.application.model.BookMarkSearchRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookMarkQueryService extends BaseQueryService<BookMark> {

    private final BookMarkRepository bookMarkRepository;
    private final BookMarkCustomRepository customRepository;

    public BookMarkQueryService(BookMarkRepository bookMarkRepository, BookMarkCustomRepository customRepository) {
        super(bookMarkRepository, new BookMarkNotFoundException());
        this.bookMarkRepository = bookMarkRepository;
        this.customRepository = customRepository;
    }

    public BookMark query(String link) {
        return bookMarkRepository.findByLink(link).orElseThrow(BookMarkNotFoundException::new);
    }

    public List<BookMark> query(BookMarkSearchRequest request){
        return customRepository.search(
                request.getBookMarkIdList(),
                request.getTitleContainsBookMarkIdList(),
                request.getSearchText(),
                request.getPageable()
        );
    }

    public List<BookMark> query(BookMarkCategoryQueryRequest request){
        if (request.getMainCategory().isEmpty()) {
            return bookMarkRepository.findByIdInOrderByMainCategoryAscSubCategoryAscCreatedDateAsc(request.getBookMarkIdList());
        }
        return bookMarkRepository.findByIdInAndMainCategoryEqualsOrderBySubCategoryAscCreatedDateAsc(
                request.getBookMarkIdList(),
                request.getMainCategory()
        );
    }

    public List<BookMark> query(BookMarkRecentQueryRequest request){
        return bookMarkRepository.findTop20ByIdInOrderByCreatedDateAsc(request.getBookMarkIdList());
    }
}
