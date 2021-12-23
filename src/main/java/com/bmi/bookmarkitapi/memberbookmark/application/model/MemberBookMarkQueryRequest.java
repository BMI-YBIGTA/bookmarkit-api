package com.bmi.bookmarkitapi.memberbookmark.application.model;

import lombok.Data;

import java.util.Optional;

@Data
public class MemberBookMarkQueryRequest {
    public Long memberId;
    public Optional<String> category;

    public MemberBookMarkQueryRequest(Long memberId, String category) {
        this.memberId = memberId;
        this.category = Optional.of(category);
    }
    public MemberBookMarkQueryRequest(Long memberId){
        this.memberId = memberId;
        this.category=Optional.empty();
    }
}
