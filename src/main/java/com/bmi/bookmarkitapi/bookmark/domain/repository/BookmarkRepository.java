package com.bmi.bookmarkitapi.bookmark.domain.repository;

import com.bmi.bookmarkitapi.bookmark.domain.model.Bookmark;
import com.bmi.bookmarkitapi.bookmark.domain.model.BookmarkStatus;
import com.bmi.bookmarkitapi.common.BaseRepository;

import java.util.List;
import java.util.Optional;

public interface BookmarkRepository extends BaseRepository<Bookmark>, BookmarkRepositoryCustom {

    Optional<Bookmark> findByLink(String link);

    List<Bookmark> findFirst20ByIdInAndStatusEqualsOrderByCreatedAtDesc(List<Long> bookmarkIds, BookmarkStatus status);
}
