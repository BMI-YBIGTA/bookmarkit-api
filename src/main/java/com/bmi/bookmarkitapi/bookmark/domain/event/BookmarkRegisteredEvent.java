package com.bmi.bookmarkitapi.bookmark.domain.event;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookmarkRegisteredEvent {

    public Long bookmarkId;
    public String link;

    @Override
    public String toString() {
        return bookmarkId.toString() + "|||" + link;
    }
}
