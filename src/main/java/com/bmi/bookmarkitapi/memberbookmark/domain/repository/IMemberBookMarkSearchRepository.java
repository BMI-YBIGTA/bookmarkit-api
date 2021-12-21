package com.bmi.bookmarkitapi.memberbookmark.domain.repository;

import com.bmi.bookmarkitapi.memberbookmark.domain.model.MemberBookMarkSearchRequest;
import com.bmi.bookmarkitapi.memberbookmark.domain.model.MemberBookMarkSearchResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IMemberBookMarkSearchRepository {

    Page<MemberBookMarkSearchResponse> search(MemberBookMarkSearchRequest searchRequest, Pageable pageable);
}