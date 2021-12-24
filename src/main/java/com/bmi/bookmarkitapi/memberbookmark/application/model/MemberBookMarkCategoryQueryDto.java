package com.bmi.bookmarkitapi.memberbookmark.application.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class MemberBookMarkCategoryQueryDto {
    public String mainCategory;
    public List<BookMarkQueryDto> bookMarkList;

    public MemberBookMarkCategoryQueryDto() {
        this.bookMarkList = new ArrayList<>();
    }
}
