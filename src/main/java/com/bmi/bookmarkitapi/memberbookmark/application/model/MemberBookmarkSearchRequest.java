package com.bmi.bookmarkitapi.memberbookmark.application.model;

import lombok.Data;

@Data
public class MemberBookmarkSearchRequest {
    public Long memberId;
    public String searchText;

    public MemberBookmarkSearchRequest(Long memberId, String searchText) {
        this.memberId = memberId;
        this.searchText = searchText;
    }
}
