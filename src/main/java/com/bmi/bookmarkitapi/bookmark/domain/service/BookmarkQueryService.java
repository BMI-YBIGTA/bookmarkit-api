package com.bmi.bookmarkitapi.bookmark.domain.service;

import com.bmi.bookmarkitapi.bookmark.domain.model.Bookmark;
import com.bmi.bookmarkitapi.bookmark.domain.model.BookmarkStatus;
import com.bmi.bookmarkitapi.bookmark.domain.repository.BookmarkCustomRepository;
import com.bmi.bookmarkitapi.bookmark.domain.repository.BookmarkRepository;
import com.bmi.bookmarkitapi.common.BaseQueryService;
import com.bmi.bookmarkitapi.common.exception.ResourceNotFoundException;
import com.bmi.bookmarkitapi.memberbookmark.application.model.BookmarkCategoryQueryRequest;
import com.bmi.bookmarkitapi.memberbookmark.application.model.BookmarkRecentQueryRequest;
import com.bmi.bookmarkitapi.memberbookmark.application.model.BookmarkSearchRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookmarkQueryService extends BaseQueryService<Bookmark> {

    private final BookmarkRepository bookmarkRepository;
    private final BookmarkCustomRepository customRepository;

    public BookmarkQueryService(BookmarkRepository bookmarkRepository, BookmarkCustomRepository customRepository) {
        super(bookmarkRepository, new ResourceNotFoundException());
        this.bookmarkRepository = bookmarkRepository;
        this.customRepository = customRepository;
    }

    public Bookmark query(String link) {
        return bookmarkRepository.findByLink(link).orElseThrow(ResourceNotFoundException::new);
    }

    public List<Bookmark> query(BookmarkSearchRequest request){
        return customRepository.search(
                request.getBookmarkIdList(),
                request.getTitleContainsBookmarkIdList(),
                request.getSearchText(),
                request.getPageable()
        );
    }

    public List<Bookmark> query(BookmarkCategoryQueryRequest request){
        if (request.getMainCategory().isEmpty()) {
            return bookmarkRepository.findByIdInAndStatusEqualsAndMainCategoryIsNotNullAndSubCategoryIsNotNullOrderByMainCategoryAscSubCategoryAscCreatedAtAsc(request.getBookmarkIdList(), BookmarkStatus.COMPLETED);
        }
        return bookmarkRepository.findByIdInAndMainCategoryEqualsAndStatusEqualsAndMainCategoryIsNotNullAndSubCategoryIsNotNullOrderBySubCategoryAscCreatedAtAsc(
                request.getBookmarkIdList(),
                request.getMainCategory(),
                BookmarkStatus.COMPLETED
        );
    }

    public List<Bookmark> query(BookmarkRecentQueryRequest request){
        return bookmarkRepository.findTop20ByIdInAndStatusEqualsAndMainCategoryIsNotNullAndSubCategoryIsNotNullOrderByCreatedAtAsc(request.getBookmarkIdList(), BookmarkStatus.COMPLETED);
    }
}
