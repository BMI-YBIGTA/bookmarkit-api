package com.bmi.bookmarkitapi.memberbookmark.application.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class MemberBookmarkCategoryQueryDto {
    public String mainCategory;
    public List<BookmarkQueryDto> bookmarkList;

    public MemberBookmarkCategoryQueryDto() {
        this.bookmarkList = new ArrayList<>();
    }
}
