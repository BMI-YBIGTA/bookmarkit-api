package com.bmi.bookmarkitapi.bookmark.domain.model;

import com.bmi.bookmarkitapi.bookmark.domain.event.BookmarkRegisteredEvent;
import com.bmi.bookmarkitapi.common.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Bookmark extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String link;
    @Column(columnDefinition = "LONGTEXT")
    private String content = "";
    private String mainCategory;
    private String subCategory;

    @Enumerated(EnumType.STRING)
    private BookmarkStatus status = BookmarkStatus.INIT;

    public Bookmark(String link) {
        this.link = link;
    }

    public Bookmark statusModify(BookmarkStatus status) {
        this.status = status;
        return this;
    }

    public Bookmark contentSet(String content) {
        this.content = content;
        return this;
    }

    public void request() {
        this.status = BookmarkStatus.REQUESTING;
    }

    public void complete() {
        this.status = BookmarkStatus.COMPLETED;
    }

    public void setCategory(String mainCategory, String subCategory){
        this.mainCategory = mainCategory;
        this.subCategory = subCategory;
    }

    public String summarizeContent(String searchText) {
        int index = content.indexOf(searchText);
        if (index<= -1){
            return "";
        }
        int startIndex = index > -1 ? index : 0;
        int endIndex = Math.min(startIndex + 200, content.length());
        return content.substring(startIndex, endIndex);
    }

    @PostPersist
    public void publishBookmarkRegisteredEvent() {
        registerEvent(new BookmarkRegisteredEvent(id, link));
    }
}
