package com.bmi.bookmarkitapi.memberbookmark.application;

import com.bmi.bookmarkitapi.memberbookmark.application.model.request.MemberBookmarkSearchRequest;
import com.bmi.bookmarkitapi.memberbookmark.application.model.response.MemberBookmarkSearchResponse;

import java.util.List;

public interface MemberBookmarkSearchService {

    List<MemberBookmarkSearchResponse> search(MemberBookmarkSearchRequest request);
}
