package com.bmi.bookmarkitapi.memberbookmark.domain.repository;

import com.bmi.bookmarkitapi.memberbookmark.domain.model.MemberBookmark;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

import static com.bmi.bookmarkitapi.memberbookmark.domain.model.QMemberBookmark.memberBookmark;

@RequiredArgsConstructor
public class MemberBookmarkRepositoryCustomImpl implements MemberBookmarkRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<MemberBookmark> search(Long memberId, String title) {
        return queryFactory.selectFrom(memberBookmark)
                .where(memberBookmark.memberId.eq(memberId), titleContains(title))
                .fetch();
    }

    private BooleanExpression titleContains(String title) {
        if (StringUtils.isEmpty(title)) {
            return null;
        }
        return memberBookmark.title.containsIgnoreCase(title);
    }
}
