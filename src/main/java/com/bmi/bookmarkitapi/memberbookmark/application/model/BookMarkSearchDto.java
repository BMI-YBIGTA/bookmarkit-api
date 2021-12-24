package com.bmi.bookmarkitapi.memberbookmark.application.model;

import com.bmi.bookmarkitapi.bookmark.domain.model.BookMarkStatus;
import lombok.Data;

@Data
public class BookMarkSearchDto {
    public String mainCategory;
    public String subCategory;
    public String title;
    public String link;
    public String content;
    public BookMarkStatus status;

    public BookMarkSearchDto(String mainCategory, String subCategory, String title, String link, String content, BookMarkStatus status) {
        this.mainCategory = mainCategory;
        this.subCategory = subCategory;
        this.title = title;
        this.link = link;
        this.content = content;
        this.status = status;
    }

    public void contentSummary(String searchText){
        int index = this.getContent().indexOf(searchText);
        int startIndex = index > -1 ? index : 0;
        int endIndex = Math.min(startIndex + 200, this.getContent().length());
        String summaryContent = this.getContent().substring(startIndex,endIndex);
        this.setContent(summaryContent);
    }
}
