package com.bmi.bookmarkitapi.memberbookmark.application.model;

import lombok.Data;

import java.util.Optional;

@Data
public class MemberBookMarkQueryRequest {
    public Long memberId;
    public Optional<String> mainCategory;

    public MemberBookMarkQueryRequest(Long memberId, String mainCategory) {
        this.memberId = memberId;
        this.mainCategory = Optional.of(mainCategory);
    }
    public MemberBookMarkQueryRequest(Long memberId){
        this.memberId = memberId;
        this.mainCategory=Optional.empty();
    }
}
