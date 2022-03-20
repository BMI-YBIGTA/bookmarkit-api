package com.bmi.bookmarkitapi.bookmark.domain.repository;

import com.bmi.bookmarkitapi.bookmark.domain.model.Bookmark;
import com.bmi.bookmarkitapi.bookmark.domain.model.BookmarkStatus;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.bmi.bookmarkitapi.bookmark.domain.model.QBookmark.bookmark;

@RequiredArgsConstructor
public class BookmarkRepositoryCustomImpl implements BookmarkRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Bookmark> search(
            List<Long> bookmarkIds, List<Long> titleSearchedBookmarkIds, String searchText, Pageable pageable
    ) {
        return queryFactory.selectFrom(bookmark)
                .where(bookmark.status.eq(BookmarkStatus.COMPLETED),
                        containsText(bookmarkIds, titleSearchedBookmarkIds, searchText))
                .orderBy(bookmark.createdAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

    @Override
    public List<Bookmark> findByMainCategory(List<Long> bookmarkIds, String mainCategory) {
        return queryFactory.selectFrom(bookmark)
                .where(bookmark.id.in(bookmarkIds),
                        bookmark.status.eq(BookmarkStatus.COMPLETED),
                        mainCategoryEquals(mainCategory))
                .orderBy(bookmark.mainCategory.asc(), bookmark.subCategory.asc())
                .fetch();
    }

    private BooleanExpression containsText(List<Long> bookmarkIds, List<Long> titleSearchedBookmarkIds, String searchText) {
        return bookmark.id.in(titleSearchedBookmarkIds)
                .or(bookmark.id.in(bookmarkIds).and(categoryOrContentContainsText(searchText)));
    }

    private BooleanExpression categoryOrContentContainsText(String searchText) {
        if (StringUtils.isEmpty(searchText)) {
            return null;
        }
        return bookmark.mainCategory.containsIgnoreCase(searchText)
                .or(bookmark.subCategory.containsIgnoreCase(searchText))
                .or(bookmark.content.containsIgnoreCase(searchText));
    }

    private BooleanExpression mainCategoryEquals(String mainCategory) {
        if (StringUtils.isEmpty(mainCategory)) {
            return null;
        }
        return bookmark.mainCategory.eq(mainCategory);
    }
}
