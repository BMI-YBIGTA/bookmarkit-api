package com.bmi.bookmarkitapi.bookmark.domain.event;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookMarkRegisteredEvent {
    public Long bookMarkId;
    public String link;

    @Override
    public String toString() {
        return bookMarkId.toString() + "|||" + link;
    }
}
