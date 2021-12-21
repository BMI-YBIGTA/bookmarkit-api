package com.bmi.bookmarkitapi.memberbookmark.domain.service;

import com.bmi.bookmarkitapi.common.BaseQueryService;
import com.bmi.bookmarkitapi.memberbookmark.domain.exception.MemberBookMarkNotFoundException;
import com.bmi.bookmarkitapi.memberbookmark.domain.model.MemberBookMark;
import com.bmi.bookmarkitapi.memberbookmark.domain.repository.MemberBookMarkRepository;
import org.springframework.stereotype.Service;

@Service
public class MemberBookMarkQueryService extends BaseQueryService<MemberBookMark> {
    public MemberBookMarkQueryService(MemberBookMarkRepository repository) {
        super(repository, new MemberBookMarkNotFoundException());
    }
}
