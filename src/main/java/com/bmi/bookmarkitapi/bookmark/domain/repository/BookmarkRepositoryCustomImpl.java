package com.bmi.bookmarkitapi.bookmark.domain.repository;

import com.bmi.bookmarkitapi.bookmark.domain.model.Bookmark;
import com.bmi.bookmarkitapi.bookmark.domain.model.BookmarkStatus;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.bmi.bookmarkitapi.bookmark.domain.model.QBookmark.bookmark;

@RequiredArgsConstructor
public class BookmarkRepositoryCustomImpl {

    private final JPAQueryFactory queryFactory;

    public List<Bookmark> search(
            List<Long> bookmarkIds,
            List<Long> titleContainsBookmarkIds,
            String searchText,
            Pageable pageable
    ) {
        return queryFactory.selectFrom(bookmark)
                .where(bookmark.status.eq(BookmarkStatus.COMPLETED),
                        containsText(bookmarkIds, titleContainsBookmarkIds, searchText))
                .orderBy(bookmark.createdAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

    private BooleanExpression containsText(List<Long> bookmarkIds, List<Long> titleContainsBookmarkIds, String searchText) {
        return bookmark.id.in(titleContainsBookmarkIds)
                .or(bookmark.id.in(bookmarkIds).and(categoryOrContentContainsText(searchText)));
    }

    private BooleanExpression categoryOrContentContainsText(String searchText) {
        if (searchText == null) {
            return null;
        }
        return bookmark.mainCategory.containsIgnoreCase(searchText)
                .or(bookmark.subCategory.containsIgnoreCase(searchText))
                .or(bookmark.content.containsIgnoreCase(searchText));
    }
}
