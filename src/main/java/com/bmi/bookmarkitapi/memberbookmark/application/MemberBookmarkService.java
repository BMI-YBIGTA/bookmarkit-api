package com.bmi.bookmarkitapi.memberbookmark.application;

import com.bmi.bookmarkitapi.memberbookmark.application.model.request.MemberBookmarkTitleModifyRequest;
import com.bmi.bookmarkitapi.memberbookmark.application.model.request.MemberBookmarkRegisterRequest;
import com.bmi.bookmarkitapi.memberbookmark.application.model.response.MemberBookmarkResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface MemberBookmarkService {

    List<MemberBookmarkResponse> getMemberBookmarks();

    MemberBookmarkResponse getMemberBookmark(Long id);

    Page<MemberBookmarkResponse> getMemberBookmarkPage(int page, int size);

    MemberBookmarkResponse register(Long memberId, MemberBookmarkRegisterRequest request);

    MemberBookmarkResponse modifyTitle(Long id, MemberBookmarkTitleModifyRequest request);
}
