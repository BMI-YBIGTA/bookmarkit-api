package com.bmi.bookmarkitapi.memberbookmark.domain.service;

import com.bmi.bookmarkitapi.common.BaseCommandService;
import com.bmi.bookmarkitapi.memberbookmark.domain.model.MemberBookmark;
import com.bmi.bookmarkitapi.memberbookmark.domain.repository.MemberBookMarkRepository;
import org.springframework.stereotype.Service;

@Service
public class MemberBookMarkCommandService extends BaseCommandService<MemberBookmark> {
    public MemberBookMarkCommandService(
            MemberBookMarkRepository repository
    ) {
        super(repository);
    }

    public MemberBookmark create(
            Long memberId,
            Long bookmarkId,
            String title
    ) {
        return this.save(
                new MemberBookmark(
                        memberId,
                        bookmarkId,
                        title
                )
        );
    }
}
