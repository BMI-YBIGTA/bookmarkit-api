package com.bmi.bookmarkitapi.userbookmark.domain.service;

import com.bmi.bookmarkitapi.common.BaseQueryService;
import com.bmi.bookmarkitapi.userbookmark.domain.exception.UserBookMarkNotFoundException;
import com.bmi.bookmarkitapi.userbookmark.domain.model.UserBookmark;
import com.bmi.bookmarkitapi.userbookmark.domain.repository.UserBookMarkRepository;
import org.springframework.stereotype.Service;

@Service
public class UserBookMarkQueryService extends BaseQueryService<UserBookmark> {
    public UserBookMarkQueryService(
            UserBookMarkRepository repository
            ) {
        super(repository, new UserBookMarkNotFoundException());
    }
}
