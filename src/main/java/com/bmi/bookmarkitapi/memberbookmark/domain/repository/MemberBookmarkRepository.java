package com.bmi.bookmarkitapi.memberbookmark.domain.repository;

import com.bmi.bookmarkitapi.common.BaseRepository;
import com.bmi.bookmarkitapi.memberbookmark.domain.model.MemberBookmark;

import java.util.List;

public interface MemberBookmarkRepository extends BaseRepository<MemberBookmark> {
    List<MemberBookmark> findByMemberId(Long memberId);
    List<MemberBookmark> findByMemberIdEqualsAndTitleContains(Long memberId, String title);
}
