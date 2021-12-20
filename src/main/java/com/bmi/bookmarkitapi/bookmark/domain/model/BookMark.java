package com.bmi.bookmarkitapi.bookmark.domain.model;

import com.bmi.bookmarkitapi.common.BaseEntity;
import com.bmi.bookmarkitapi.memberbookmark.domain.model.MemberBookMarkStatus;
import com.bmi.bookmarkitapi.memberbookmark.domain.model.MemberBookmark;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BookMark extends BaseEntity {
    @Id
    @GeneratedValue
    private Long id;
    private String header;
    private String link;
    private String content;
    @Column(nullable = true)
    private String category;
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

    public BookMark setCategory(String category){
        this.category = category;
        return this;
    }
}
