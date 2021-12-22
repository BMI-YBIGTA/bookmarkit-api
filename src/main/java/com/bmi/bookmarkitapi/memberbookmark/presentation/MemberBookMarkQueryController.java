package com.bmi.bookmarkitapi.memberbookmark.presentation;

import com.bmi.bookmarkitapi.common.BaseQueryController;
import com.bmi.bookmarkitapi.memberbookmark.domain.model.MemberBookMark;
import com.bmi.bookmarkitapi.memberbookmark.domain.service.MemberBookMarkQueryService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/memberbookmark")
public class MemberBookMarkQueryController extends BaseQueryController<MemberBookMark> {
    public MemberBookMarkQueryController(MemberBookMarkQueryService queryService) {
        super(queryService);
    }
}
