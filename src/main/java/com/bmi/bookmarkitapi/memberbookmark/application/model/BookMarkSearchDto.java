package com.bmi.bookmarkitapi.memberbookmark.application.model;

import lombok.Data;

@Data
public class BookMarkSearchDto {
    public Long memberBookMarkId;
    public String category;
    public String title;
    public String link;
    public String content;

    public BookMarkSearchDto(Long memberBookMarkId, String category, String title, String link, String content) {
        this.memberBookMarkId = memberBookMarkId;
        this.category = category;
        this.title = title;
        this.link = link;
        this.content = content;
    }

    public void contentSummary(String searchText){
        int index = this.getContent().indexOf(searchText);
        int startIndex = index > -1 ? index : 0;
        int endIndex = Math.min(startIndex + 200, this.getContent().length());
        String summaryContent = this.getContent().substring(startIndex,endIndex);
        this.setContent(summaryContent);
    }
}
