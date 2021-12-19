package com.bmi.bookmarkitapi.userbookmark.domain.service;

import com.bmi.bookmarkitapi.common.BaseCommandService;
import com.bmi.bookmarkitapi.userbookmark.domain.model.UserBookmark;
import com.bmi.bookmarkitapi.userbookmark.domain.repository.UserBookMarkRepository;
import org.springframework.stereotype.Service;

@Service
public class UserBookMarkCommandService extends BaseCommandService<UserBookmark> {
    public UserBookMarkCommandService(
            UserBookMarkRepository repository
    ) {
        super(repository);
    }
}
