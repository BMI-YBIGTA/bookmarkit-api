package com.bmi.bookmarkitapi.memberbookmark.application.model.response;

import com.bmi.bookmarkitapi.bookmark.domain.model.BookmarkStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class MemberBookmarkCategoryQueryResponse {
    private String mainCategory;
    private List<SubCategory> subCategories;

    @Getter
    @AllArgsConstructor
    public static class SubCategory {
        private final String subCategory;
        private List<MemberBookmarkDto> memberBookmarks;

        @Getter
        public static class MemberBookmarkDto {
            private final Long memberBookmarkId;
            private final String title;
            private final String link;
            private final String createdDate;
            private final BookmarkStatus status;

            public MemberBookmarkDto(BookmarkQueryResponse bookmark) {
                memberBookmarkId = bookmark.getMemberBookmarkId();
                title = bookmark.getTitle();
                link = bookmark.getLink();
                createdDate = bookmark.getCreatedDate();
                status = bookmark.getStatus();
            }
        }
    }
}
