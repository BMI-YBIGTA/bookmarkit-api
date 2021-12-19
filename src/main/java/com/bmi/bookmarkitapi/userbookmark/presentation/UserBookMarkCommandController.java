package com.bmi.bookmarkitapi.userbookmark.presentation;

import com.bmi.bookmarkitapi.userbookmark.application.UserBookMarkRegistrationService;
import com.bmi.bookmarkitapi.userbookmark.application.UserBookMarkTitleModificationService;
import com.bmi.bookmarkitapi.userbookmark.application.model.UserBookMarkRegistrationRequest;
import com.bmi.bookmarkitapi.userbookmark.application.model.UserBookMarkTitleModificationRequest;
import com.bmi.bookmarkitapi.userbookmark.domain.model.UserBookmark;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("userbookmark")
public class UserBookMarkCommandController {
    private final UserBookMarkRegistrationService registrationService;
    private final UserBookMarkTitleModificationService titleModificationService;

    @PostMapping
    public UserBookmark register(
            @RequestBody UserBookMarkRegistrationRequest request
            ) {
        return registrationService.register(request);
    }

    @PatchMapping("{id}")
    public UserBookmark titleModify(
            @PathVariable Long id,
            @RequestBody UserBookMarkTitleModificationRequest request
            ) {
        return titleModificationService.modify(id, request);
    }
}
