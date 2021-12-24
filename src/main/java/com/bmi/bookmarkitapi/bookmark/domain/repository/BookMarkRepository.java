package com.bmi.bookmarkitapi.bookmark.domain.repository;

import com.bmi.bookmarkitapi.bookmark.domain.model.BookMark;
import com.bmi.bookmarkitapi.common.BaseRepository;

import java.util.List;
import java.util.Optional;

public interface BookMarkRepository extends BaseRepository<BookMark> {

    Optional<BookMark> findByLink(String link);
    List<BookMark> findByIdInAndMainCategoryEqualsOrderBySubCategoryAscCreatedDateAsc(List<Long> bookMarkIdList , String mainCategory);
    List<BookMark> findByIdInOrderByMainCategoryAscSubCategoryAscCreatedDateAsc(List<Long> bookMarkIdList);
    List<BookMark> findTop20ByIdInOrderByCreatedDateAsc(List<Long> bookMarkIdList);
}
