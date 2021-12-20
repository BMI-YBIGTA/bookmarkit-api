package com.bmi.bookmarkitapi.bookmark.domain.repository;

import com.bmi.bookmarkitapi.bookmark.domain.model.BookMark;
import com.bmi.bookmarkitapi.common.BaseRepository;

import java.util.Optional;

public interface BookMarkRepository extends BaseRepository<BookMark> {

    Optional<BookMark> findByLink(String link);
}