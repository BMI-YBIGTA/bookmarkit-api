package com.bmi.bookmarkitapi.memberbookmark.application.model;

import com.bmi.bookmarkitapi.memberbookmark.domain.model.MemberBookmark;
import lombok.Getter;

public class MemberBookmarkDto {

    @Getter
    public static class Response {
        private final Long memberId;
        private final Long bookmarkId;
        private final String title;

        public Response(MemberBookmark memberBookmark) {
            memberId = memberBookmark.getMemberId();
            bookmarkId = memberBookmark.getBookmarkId();
            title = memberBookmark.getTitle();
        }
    }
}
