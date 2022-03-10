package com.bmi.bookmarkitapi.bookmark.domain.repository;

import com.bmi.bookmarkitapi.bookmark.domain.model.Bookmark;
import com.bmi.bookmarkitapi.bookmark.domain.model.BookmarkStatus;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.bmi.bookmarkitapi.bookmark.domain.model.QBookmark.bookmark;

@RequiredArgsConstructor
@Repository
public class BookmarkCustomRepository {

    private final JPAQueryFactory queryFactory;

    public List<Bookmark> search(
            List<Long> bookmarkIdList,
            List<Long> titleContainsBookmarkIdList,
            String searchText,
            Pageable pageable
    ) {
        return queryFactory.selectFrom(bookmark)
                .where(bookmark.status.eq(BookmarkStatus.COMPLETED)
                    .and(bookmark.mainCategory.isNotNull())
                    .and(bookmark.subCategory.isNotNull())
                    .and((bookmark.id.in(titleContainsBookmarkIdList))
                        .or(bookmark.id.in(bookmarkIdList)
                            .and(bookmark.mainCategory.contains(searchText)
                                    .or(bookmark.subCategory.contains(searchText))
                                    .or(bookmark.content.contains(searchText))
                            )
                        )
                    )
                )
                .orderBy(bookmark.createdAt.asc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }
}
