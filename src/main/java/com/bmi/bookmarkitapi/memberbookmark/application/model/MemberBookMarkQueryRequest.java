package com.bmi.bookmarkitapi.memberbookmark.application.model;

import lombok.Data;

@Data
public class MemberBookMarkQueryRequest {
    public Long memberId;
    public String mainCategory;

    public MemberBookMarkQueryRequest(Long memberId){
        this.memberId = memberId;
    }

    public MemberBookMarkQueryRequest(Long memberId, String mainCategory) {
        this.memberId = memberId;
        this.mainCategory = mainCategory;
    }
}
