package com.bmi.bookmarkitapi.bookmark.infrastructure;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookMarkClassifiedEvent {
    public Long bookMarkId;
    public String category;
}
