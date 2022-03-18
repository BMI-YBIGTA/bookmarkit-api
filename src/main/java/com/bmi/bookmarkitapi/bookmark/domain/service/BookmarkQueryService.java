package com.bmi.bookmarkitapi.bookmark.domain.service;

import com.bmi.bookmarkitapi.bookmark.domain.model.Bookmark;
import com.bmi.bookmarkitapi.bookmark.domain.model.BookmarkStatus;
import com.bmi.bookmarkitapi.bookmark.domain.repository.BookmarkRepository;
import com.bmi.bookmarkitapi.common.BaseQueryService;
import com.bmi.bookmarkitapi.common.exception.ResourceNotFoundException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookmarkQueryService extends BaseQueryService<Bookmark> {

    private final BookmarkRepository bookmarkRepository;

    public BookmarkQueryService(BookmarkRepository bookmarkRepository) {
        super(bookmarkRepository);
        this.bookmarkRepository = bookmarkRepository;
    }

    public Bookmark findByLink(String link) {
        return bookmarkRepository.findByLink(link)
                .orElseThrow(() -> new ResourceNotFoundException("해당 링크를 가진 북마크가 존재하지 않습니다"));
    }

    public List<Bookmark> search(
            List<Long> bookmarkIds, List<Long> titleSearchedBookmarkIds, String searchText, Pageable pageable
    ){
        return bookmarkRepository.search(bookmarkIds, titleSearchedBookmarkIds, searchText, pageable);
    }

    public List<Bookmark> findByMainCategory(List<Long> bookmarkIds, String mainCategory){
        return bookmarkRepository.findByMainCategory(bookmarkIds, mainCategory);
    }

    public List<Bookmark> findRecent20Bookmarks(List<Long> bookmarkIds){
        return bookmarkRepository.findFirst20ByIdInAndStatusEqualsOrderByCreatedAtDesc(bookmarkIds, BookmarkStatus.COMPLETED);
    }
}
