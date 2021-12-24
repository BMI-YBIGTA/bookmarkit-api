package com.bmi.bookmarkitapi.bookmark.domain.repository;

import com.bmi.bookmarkitapi.bookmark.domain.model.BookMark;
import com.bmi.bookmarkitapi.memberbookmark.domain.model.QMemberBookMark;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.bmi.bookmarkitapi.bookmark.domain.model.QBookMark.bookMark;
import static com.bmi.bookmarkitapi.memberbookmark.domain.model.QMemberBookMark.memberBookMark;

@Repository
@RequiredArgsConstructor
public class BookMarkCustomRepository {
    private final JPAQueryFactory queryFactory;

    public List<BookMark> search(
            List<Long> bookMarkIdList,
            List<Long> titleContainsBookMarkIdList,
            String searchText,
            Pageable pageable) {

        List<BookMark> result = queryFactory.selectFrom(bookMark)
                .where(bookMark.id.in(titleContainsBookMarkIdList)
                    .or(bookMark.id.in(bookMarkIdList)
                        .and(bookMark.mainCategory.contains(searchText)
                                .or(bookMark.subCategory.contains(searchText))
                                .or(bookMark.content.contains(searchText))
                        )
                    )
                )
                .orderBy(bookMark.createdDate.asc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        return result;
    }

}
