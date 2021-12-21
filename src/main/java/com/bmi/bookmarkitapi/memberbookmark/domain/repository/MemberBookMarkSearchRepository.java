package com.bmi.bookmarkitapi.memberbookmark.domain.repository;

import com.bmi.bookmarkitapi.memberbookmark.domain.model.MemberBookMarkSearchRequest;
import com.bmi.bookmarkitapi.memberbookmark.domain.model.MemberBookMarkSearchResponse;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.bmi.bookmarkitapi.bookmark.domain.model.QBookMark.bookMark;
import static com.bmi.bookmarkitapi.memberbookmark.domain.model.QMemberBookmark.memberBookmark;

@Repository
@RequiredArgsConstructor
public class MemberBookMarkSearchRepository implements IMemberBookMarkSearchRepository{
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<MemberBookMarkSearchResponse> search(MemberBookMarkSearchRequest searchRequest , Pageable pageable) {


        List<MemberBookMarkSearchResponse> result = jpaQueryFactory.select(Projections.constructor(MemberBookMarkSearchResponse.class,
                        memberBookmark.id, bookMark.category, memberBookmark.title, bookMark.link, bookMark.content))
                .from(memberBookmark).join(bookMark).on(memberBookmark.bookmarkId.eq(bookMark.id))
                .where(
                        memberBookmark.memberId.eq(searchRequest.memberId)
                                .and(bookMark.category.contains(searchRequest.getSearchText())
                                        .or(memberBookmark.title.contains(searchRequest.getSearchText()))
                                        .or(bookMark.content.contains(searchRequest.getSearchText()))
                                )
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize()).fetch();

        return new PageImpl<>(result,pageable,result.size());
    }
}
