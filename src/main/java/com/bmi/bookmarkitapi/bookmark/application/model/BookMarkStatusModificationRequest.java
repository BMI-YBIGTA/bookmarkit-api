package com.bmi.bookmarkitapi.bookmark.application.model;

import com.bmi.bookmarkitapi.bookmark.domain.model.BookMarkStatus;
import lombok.Data;

@Data
public class BookMarkStatusModificationRequest {
    public BookMarkStatus status;

    public BookMarkStatusModificationRequest(BookMarkStatus status) {
        this.status = status;
    }
}
