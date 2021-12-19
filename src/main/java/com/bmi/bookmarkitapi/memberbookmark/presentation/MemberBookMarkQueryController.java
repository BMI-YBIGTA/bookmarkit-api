package com.bmi.bookmarkitapi.memberbookmark.presentation;

import com.bmi.bookmarkitapi.common.BaseQueryController;
import com.bmi.bookmarkitapi.memberbookmark.domain.model.MemberBookmark;
import com.bmi.bookmarkitapi.memberbookmark.domain.service.MemberBookMarkQueryService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/memberbookmark")
public class MemberBookMarkQueryController extends BaseQueryController<MemberBookmark> {
    public MemberBookMarkQueryController(MemberBookMarkQueryService queryService) {
        super(queryService);
    }
}
