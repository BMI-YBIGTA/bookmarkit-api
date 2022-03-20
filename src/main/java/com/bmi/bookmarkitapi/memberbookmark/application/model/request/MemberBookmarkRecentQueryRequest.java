package com.bmi.bookmarkitapi.memberbookmark.application.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MemberBookmarkRecentQueryRequest {
    private Long memberId;
}
