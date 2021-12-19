package com.bmi.bookmarkitapi.memberbookmark.presentation;

import com.bmi.bookmarkitapi.memberbookmark.application.MemberBookMarkRegistrationService;
import com.bmi.bookmarkitapi.memberbookmark.application.MemberBookMarkTitleModificationService;
import com.bmi.bookmarkitapi.memberbookmark.application.model.MemberBookMarkRegistrationRequest;
import com.bmi.bookmarkitapi.memberbookmark.application.model.MemberBookMarkTitleModificationRequest;
import com.bmi.bookmarkitapi.memberbookmark.domain.model.MemberBookmark;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("memberbookmark")
public class MemberBookMarkCommandController {
    private final MemberBookMarkRegistrationService registrationService;
    private final MemberBookMarkTitleModificationService titleModificationService;

    @PostMapping
    public MemberBookmark register(
            @RequestBody MemberBookMarkRegistrationRequest request
            ) {
        return registrationService.register(request);
    }

    @PatchMapping("{id}")
    public MemberBookmark titleModify(
            @PathVariable Long id,
            @RequestBody MemberBookMarkTitleModificationRequest request
            ) {
        return titleModificationService.modify(id, request);
    }
}
