package com.bmi.bookmarkitapi.bookmark.domain.model;

import com.bmi.bookmarkitapi.bookmark.domain.event.BookmarkRegisteredEvent;
import com.bmi.bookmarkitapi.common.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
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

    public void setContent(String content) {
        this.content = content;
    }

    public void setCategory(String mainCategory, String subCategory) {
        this.mainCategory = mainCategory;
        this.subCategory = subCategory;
    }

    public void modifyStatus(BookmarkStatus status) {
        this.status = status;
    }

    public String summarizeContent(String searchText) {
        int startIndex = content.indexOf(searchText);
        if (startIndex == -1){
            return "";
        }
        int endIndex = Math.min(startIndex + 200, content.length());
        return content.substring(startIndex, endIndex);
    }

    @PostPersist
    public void publishBookmarkRegisteredEvent() {
        registerEvent(new BookmarkRegisteredEvent(id, link));
    }
}
