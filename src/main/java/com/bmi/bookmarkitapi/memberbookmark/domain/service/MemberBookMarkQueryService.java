package com.bmi.bookmarkitapi.memberbookmark.domain.service;

import com.bmi.bookmarkitapi.common.BaseQueryService;
import com.bmi.bookmarkitapi.memberbookmark.domain.exception.MemberBookMarkNotFoundException;
import com.bmi.bookmarkitapi.memberbookmark.domain.model.MemberBookmark;
import com.bmi.bookmarkitapi.memberbookmark.domain.repository.MemberBookMarkRepository;
import org.springframework.stereotype.Service;

@Service
public class MemberBookMarkQueryService extends BaseQueryService<MemberBookmark> {
    public MemberBookMarkQueryService(MemberBookMarkRepository repository) {
        super(repository, new MemberBookMarkNotFoundException());
    }
}
