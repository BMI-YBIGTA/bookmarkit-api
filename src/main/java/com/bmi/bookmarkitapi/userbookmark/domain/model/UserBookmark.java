package com.bmi.bookmarkitapi.userbookmark.domain.model;

import com.bmi.bookmarkitapi.common.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserBookmark extends BaseEntity {
    @Id @GeneratedValue
    private Long id;
    private Long userId;
    private Long bookmarkId;
    private String title;
    @Enumerated(EnumType.STRING)
    private UserBookMarkStatus status = UserBookMarkStatus.INIT;

    public UserBookmark(
            Long userId,
            Long bookMarkId,
            String title
    ) {
        this.userId = userId;
        this.bookmarkId = bookMarkId;
        this.title = title;
    }

    public UserBookmark titleModify(String title) {
        this.title = title;
        return this;
    }
}
