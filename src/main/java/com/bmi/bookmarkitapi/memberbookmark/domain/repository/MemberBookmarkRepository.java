package com.bmi.bookmarkitapi.memberbookmark.domain.repository;

import com.bmi.bookmarkitapi.common.BaseRepository;
import com.bmi.bookmarkitapi.memberbookmark.domain.model.MemberBookmark;

import java.util.List;

public interface MemberBookmarkRepository extends BaseRepository<MemberBookmark>, MemberBookmarkRepositoryCustom {

    List<MemberBookmark> findByMemberId(Long memberId);
}
