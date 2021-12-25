package com.bmi.bookmarkitapi.bookmark.domain.repository;

import com.bmi.bookmarkitapi.bookmark.domain.model.BookMark;
import com.bmi.bookmarkitapi.bookmark.domain.model.BookMarkStatus;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.bmi.bookmarkitapi.bookmark.domain.model.QBookMark.bookMark;

@Repository
@RequiredArgsConstructor
public class BookMarkCustomRepository {
    private final JPAQueryFactory queryFactory;

    public List<BookMark> search(
            List<Long> bookMarkIdList,
            List<Long> titleContainsBookMarkIdList,
            String searchText,
            Pageable pageable
    ) {
        return queryFactory.selectFrom(bookMark)
                .where(bookMark.status.eq(BookMarkStatus.COMPLETED)
                    .and(bookMark.mainCategory.isNotNull())
                    .and(bookMark.subCategory.isNotNull())
                    .and((bookMark.id.in(titleContainsBookMarkIdList))
                        .or(bookMark.id.in(bookMarkIdList)
                            .and(bookMark.mainCategory.contains(searchText)
                                    .or(bookMark.subCategory.contains(searchText))
                                    .or(bookMark.content.contains(searchText))
                            )
                        )
                    )
                )
                .orderBy(bookMark.createdDate.asc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }
}
