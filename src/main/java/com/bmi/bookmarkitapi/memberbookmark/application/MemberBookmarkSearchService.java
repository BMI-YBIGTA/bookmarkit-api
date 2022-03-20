package com.bmi.bookmarkitapi.memberbookmark.application;

import com.bmi.bookmarkitapi.memberbookmark.application.model.request.MemberBookmarkSearchRequest;
import com.bmi.bookmarkitapi.memberbookmark.application.model.response.MemberBookmarkSearchResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MemberBookmarkSearchService {

    Page<MemberBookmarkSearchResponse> search(MemberBookmarkSearchRequest request, Pageable pageable);
}
