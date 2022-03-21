package com.bmi.bookmarkitapi.bookmark.domain.repository;

import com.bmi.bookmarkitapi.bookmark.domain.model.Bookmark;

import java.util.List;

public interface BookmarkRepositoryCustom {

    List<Bookmark> search(Long id, List<Long> bookmarkIds, List<Long> titleSearchedBookmarkIds, String searchText, int pageSize);

    List<Bookmark> findByMainCategory(List<Long> bookmarkIds, String mainCategory);
}
