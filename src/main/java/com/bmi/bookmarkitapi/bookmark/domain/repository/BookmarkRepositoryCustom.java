package com.bmi.bookmarkitapi.bookmark.domain.repository;

import com.bmi.bookmarkitapi.bookmark.domain.model.Bookmark;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookmarkRepositoryCustom {

    List<Bookmark> search(List<Long> bookmarkIds, List<Long> titleSearchedBookmarkIds, String searchText, Pageable pageable);

    List<Bookmark> findByCategory(List<Long> bookmarkIds, String mainCategory);
}
