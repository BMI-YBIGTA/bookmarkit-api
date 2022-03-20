package com.bmi.bookmarkitapi.memberbookmark.domain.repository;

import com.bmi.bookmarkitapi.memberbookmark.domain.model.MemberBookmark;

import java.util.List;

public interface MemberBookmarkRepositoryCustom {

    List<MemberBookmark> search(Long memberId, String title);
}
