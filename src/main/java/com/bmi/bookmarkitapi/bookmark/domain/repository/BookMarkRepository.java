package com.bmi.bookmarkitapi.bookmark.domain.repository;

import com.bmi.bookmarkitapi.bookmark.domain.model.BookMark;
import com.bmi.bookmarkitapi.bookmark.domain.model.BookMarkStatus;
import com.bmi.bookmarkitapi.common.BaseRepository;

import java.util.List;
import java.util.Optional;

public interface BookMarkRepository extends BaseRepository<BookMark> {

    Optional<BookMark> findByLink(String link);
    List<BookMark> findByIdInAndMainCategoryEqualsAndStatusEqualsAndMainCategoryIsNotNullAndSubCategoryIsNotNullOrderBySubCategoryAscCreatedDateAsc(List<Long> bookMarkIdList , String mainCategory, BookMarkStatus status);
    List<BookMark> findByIdInAndStatusEqualsAndMainCategoryIsNotNullAndSubCategoryIsNotNullOrderByMainCategoryAscSubCategoryAscCreatedDateAsc(List<Long> bookMarkIdList , BookMarkStatus status);
    List<BookMark> findTop20ByIdInAndStatusEqualsAndMainCategoryIsNotNullAndSubCategoryIsNotNullOrderByCreatedDateAsc(List<Long> bookMarkIdList, BookMarkStatus status);
}
