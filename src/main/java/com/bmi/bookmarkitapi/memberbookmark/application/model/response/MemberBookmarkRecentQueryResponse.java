package com.bmi.bookmarkitapi.memberbookmark.application.model.response;

import com.bmi.bookmarkitapi.bookmark.domain.model.BookmarkStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Data
@AllArgsConstructor
public class MemberBookmarkRecentQueryResponse {
    private String createdDate;
    private List<MemberBookmarkDto> memberBookmarks;

    @Getter
    public static class MemberBookmarkDto {
        private final Long memberBookmarkId;
        private final String mainCategory;
        private final String subCategory;
        private final String title;
        private final String link;
        private final BookmarkStatus status;

        public MemberBookmarkDto(BookmarkQueryResponse bookmark) {
            memberBookmarkId = bookmark.getMemberBookmarkId();
            mainCategory = bookmark.getMainCategory();
            subCategory = bookmark.getSubCategory();
            title = bookmark.getTitle();
            link = bookmark.getLink();
            status = bookmark.getStatus();
        }
    }
}
