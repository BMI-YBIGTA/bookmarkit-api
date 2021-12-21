package com.bmi.bookmarkitapi.bookmark.domain.event;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookMarkRegisteredEvent {
    public Long bookMarkId;
    public String header;
    public String content;
}
