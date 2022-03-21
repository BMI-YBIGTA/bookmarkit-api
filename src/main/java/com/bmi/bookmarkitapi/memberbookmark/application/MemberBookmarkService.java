package com.bmi.bookmarkitapi.memberbookmark.application;

import com.bmi.bookmarkitapi.memberbookmark.application.model.request.MemberBookmarkRegisterRequest;
import com.bmi.bookmarkitapi.memberbookmark.application.model.request.MemberBookmarkTitleModifyRequest;
import com.bmi.bookmarkitapi.memberbookmark.application.model.response.MemberBookmarkResponse;

public interface MemberBookmarkService {

    MemberBookmarkResponse register(Long memberId, MemberBookmarkRegisterRequest request);

    MemberBookmarkResponse modifyTitle(Long id, MemberBookmarkTitleModifyRequest request);
}
