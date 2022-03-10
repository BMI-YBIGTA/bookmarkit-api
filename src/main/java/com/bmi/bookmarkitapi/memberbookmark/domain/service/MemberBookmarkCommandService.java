package com.bmi.bookmarkitapi.memberbookmark.domain.service;

import com.bmi.bookmarkitapi.common.BaseCommandService;
import com.bmi.bookmarkitapi.memberbookmark.domain.model.MemberBookmark;
import com.bmi.bookmarkitapi.memberbookmark.domain.repository.MemberBookmarkRepository;
import org.springframework.stereotype.Service;

@Service
public class MemberBookmarkCommandService extends BaseCommandService<MemberBookmark> {

    public MemberBookmarkCommandService(MemberBookmarkRepository memberBookmarkRepository) {
        super(memberBookmarkRepository);
    }

    public MemberBookmark create(Long memberId, Long bookmarkId, String title) {
        return this.save(new MemberBookmark(memberId, bookmarkId, title));
    }
}
