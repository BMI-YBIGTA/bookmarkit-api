package com.bmi.bookmarkitapi.memberbookmark.application.model;

import lombok.Data;

@Data
public class MemberBookmarkQueryRequest {
    public Long memberId;
    public String mainCategory;

    public MemberBookmarkQueryRequest(Long memberId){
        this.memberId = memberId;
    }

    public MemberBookmarkQueryRequest(Long memberId, String mainCategory) {
        this.memberId = memberId;
        this.mainCategory = mainCategory;
    }
}
