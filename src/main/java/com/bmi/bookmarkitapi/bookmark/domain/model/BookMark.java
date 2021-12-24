package com.bmi.bookmarkitapi.bookmark.domain.model;

import com.bmi.bookmarkitapi.bookmark.domain.event.BookMarkRegisteredEvent;
import com.bmi.bookmarkitapi.common.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BookMark extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String header;
    private String link;
    private String content;
    private String mainCategory;
    private String subCategory;

    @Enumerated(EnumType.STRING)
    private BookMarkStatus status = BookMarkStatus.INIT;

    public BookMark(String header, String link, String content) {
        this.header = header;
        this.link = link;
        this.content = content;
    }

    public BookMark statusModify(BookMarkStatus status) {
        this.status = status;
        return this;
    }

    public void request() {
        this.status = BookMarkStatus.REQUESTING;
    }

    public void complete() {
        this.status = BookMarkStatus.COMPLETED;
    }

    public void setCategory(String mainCategory, String subCategory){
        this.mainCategory = mainCategory;
        this.subCategory = subCategory;
    }

    public String summarizeContent(String searchText) {
        int index = content.indexOf(searchText);
        int startIndex = index > -1 ? index : 0;
        int endIndex = Math.min(startIndex + 200, content.length());
        return content.substring(startIndex, endIndex);
    }

    @PostPersist
    public void publishBookMarkRegisteredEvent() {
        registerEvent(new BookMarkRegisteredEvent(id, header, content));
    }
}
