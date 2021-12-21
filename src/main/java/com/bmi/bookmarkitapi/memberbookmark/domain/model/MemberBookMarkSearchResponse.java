package com.bmi.bookmarkitapi.memberbookmark.domain.model;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;
import lombok.Data;

import java.util.List;

@Data
public class MemberBookMarkSearchResponse{
    public Long memberBookMarkId;
    public String category;
    public String title;
    public String link;
    public String content;

    public MemberBookMarkSearchResponse(Long memberBookMarkId, String category, String title, String link, String content) {
        this.memberBookMarkId = memberBookMarkId;
        this.category = category;
        this.title = title;
        this.link = link;
        this.content = content;
    }
}