package com.bmi.bookmarkitapi.memberbookmark.domain.model;

import com.bmi.bookmarkitapi.common.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberBookMark extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long memberId;
    private Long bookmarkId;
    private String title;

    public MemberBookMark(
            Long memberId,
            Long bookMarkId,
            String title
    ) {
        this.memberId = memberId;
        this.bookmarkId = bookMarkId;
        this.title = title;
    }

    public void titleModify(String title) {
        this.title = title;
    }
}
