package com.bmi.bookmarkitapi.memberbookmark.domain.service;

import com.bmi.bookmarkitapi.common.BaseCommandService;
import com.bmi.bookmarkitapi.memberbookmark.domain.model.MemberBookMark;
import com.bmi.bookmarkitapi.memberbookmark.domain.repository.MemberBookMarkRepository;
import org.springframework.stereotype.Service;

@Service
public class MemberBookMarkCommandService extends BaseCommandService<MemberBookMark> {
    public MemberBookMarkCommandService(
            MemberBookMarkRepository repository
    ) {
        super(repository);
    }

    public MemberBookMark create(
            Long memberId,
            Long bookmarkId,
            String title
    ) {
        return this.save(
                new MemberBookMark(
                        memberId,
                        bookmarkId,
                        title
                )
        );
    }
}
