package com.bmi.bookmarkitapi.memberbookmark.application.model.response;

import com.bmi.bookmarkitapi.memberbookmark.domain.model.MemberBookmark;
import lombok.Getter;

@Getter
public class MemberBookmarkResponse {
    private final Long memberId;
    private final Long bookmarkId;
    private final String title;

    public MemberBookmarkResponse(MemberBookmark memberBookmark) {
        memberId = memberBookmark.getMemberId();
        bookmarkId = memberBookmark.getBookmarkId();
        title = memberBookmark.getTitle();
    }
}
