package com.bmi.bookmarkitapi.memberbookmark.application;

import com.bmi.bookmarkitapi.memberbookmark.domain.model.MemberBookMarkSearchRequest;
import com.bmi.bookmarkitapi.memberbookmark.domain.model.MemberBookMarkSearchResponse;
import com.bmi.bookmarkitapi.memberbookmark.domain.service.MemberBookMarkQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberBookMarkSearchService {
    private final MemberBookMarkQueryService queryService;

    public Page<MemberBookMarkSearchResponse> search(MemberBookMarkSearchRequest searchRequest, Pageable pageable){
        return queryService.query(searchRequest,pageable);
    }
}
