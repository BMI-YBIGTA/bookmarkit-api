package com.bmi.bookmarkitapi.bookmark.domain.repository;

import com.bmi.bookmarkitapi.bookmark.domain.model.Bookmark;
import com.bmi.bookmarkitapi.bookmark.domain.model.BookmarkStatus;
import com.bmi.bookmarkitapi.common.BaseRepository;

import java.util.List;
import java.util.Optional;

public interface BookmarkRepository extends BaseRepository<Bookmark> {

    Optional<Bookmark> findByLink(String link);
    List<Bookmark> findByIdInAndMainCategoryEqualsAndStatusEqualsAndMainCategoryIsNotNullAndSubCategoryIsNotNullOrderBySubCategoryAscCreatedDateAsc(List<Long> bookmarkIdList , String mainCategory, BookmarkStatus status);
    List<Bookmark> findByIdInAndStatusEqualsAndMainCategoryIsNotNullAndSubCategoryIsNotNullOrderByMainCategoryAscSubCategoryAscCreatedDateAsc(List<Long> bookmarkIdList , BookmarkStatus status);
    List<Bookmark> findTop20ByIdInAndStatusEqualsAndMainCategoryIsNotNullAndSubCategoryIsNotNullOrderByCreatedDateAsc(List<Long> bookmarkIdList, BookmarkStatus status);
}
