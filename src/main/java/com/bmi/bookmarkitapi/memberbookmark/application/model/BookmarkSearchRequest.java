package com.bmi.bookmarkitapi.memberbookmark.application.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Data
@AllArgsConstructor
public class BookmarkSearchRequest {
    public List<Long> bookmarkIds;
    public List<Long> titleSearchedBookmarkIds;
    public String searchText;
    public Pageable pageable;
}
