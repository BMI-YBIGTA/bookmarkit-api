package com.bmi.bookmarkitapi.userbookmark.presentation;

import com.bmi.bookmarkitapi.common.BaseQueryController;
import com.bmi.bookmarkitapi.userbookmark.domain.model.UserBookmark;
import com.bmi.bookmarkitapi.userbookmark.domain.service.UserBookMarkQueryService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/userbookmark")
public class UserBookMarkQueryController extends BaseQueryController<UserBookmark> {
    public UserBookMarkQueryController(UserBookMarkQueryService queryService) {
        super(queryService);
    }
}
