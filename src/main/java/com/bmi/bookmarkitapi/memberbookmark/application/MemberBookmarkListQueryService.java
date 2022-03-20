package com.bmi.bookmarkitapi.memberbookmark.application;

import com.bmi.bookmarkitapi.memberbookmark.application.model.request.MemberBookmarkCategoryQueryRequest;
import com.bmi.bookmarkitapi.memberbookmark.application.model.request.MemberBookmarkRecentQueryRequest;
import com.bmi.bookmarkitapi.memberbookmark.application.model.response.MemberBookmarkCategoryQueryResponse;
import com.bmi.bookmarkitapi.memberbookmark.application.model.response.MemberBookmarkRecentQueryResponse;

import java.util.List;

public interface MemberBookmarkListQueryService {

    List<MemberBookmarkCategoryQueryResponse> getBookmarksByCategory(MemberBookmarkCategoryQueryRequest request);

    List<MemberBookmarkRecentQueryResponse> getRecentBookmarks(MemberBookmarkRecentQueryRequest request);
}
