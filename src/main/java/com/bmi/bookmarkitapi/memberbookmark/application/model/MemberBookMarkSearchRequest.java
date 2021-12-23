package com.bmi.bookmarkitapi.memberbookmark.application.model;

import lombok.Data;

@Data
public class MemberBookMarkSearchRequest {
    public Long memberId;
    public String searchText;

    public MemberBookMarkSearchRequest(Long memberId, String searchText) {
        this.memberId = memberId;
        this.searchText = searchText;
    }
}
