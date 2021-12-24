package com.bmi.bookmarkitapi.memberbookmark.domain.repository;

import com.bmi.bookmarkitapi.common.BaseRepository;
import com.bmi.bookmarkitapi.memberbookmark.domain.model.MemberBookMark;

import java.util.List;

public interface MemberBookMarkRepository extends BaseRepository<MemberBookMark> {
    List<MemberBookMark> findByMemberId(Long memberId);
    List<MemberBookMark> findByMemberIdEqualsAndTitleContains(Long memberId, String title);
}
