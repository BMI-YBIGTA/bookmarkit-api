package com.bmi.bookmarkitapi.memberbookmark.domain.model;

import com.bmi.bookmarkitapi.common.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberBookmark extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long memberId;
    private Long bookmarkId;
    private String title;

    public MemberBookmark(Long memberId, Long bookmarkId, String title) {
        this.memberId = memberId;
        this.bookmarkId = bookmarkId;
        this.title = title;
    }

    public void titleModify(String title) {
        this.title = title;
    }
}
