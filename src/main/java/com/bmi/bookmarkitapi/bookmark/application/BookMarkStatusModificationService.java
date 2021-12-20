package com.bmi.bookmarkitapi.bookmark.application;

import com.bmi.bookmarkitapi.bookmark.application.model.BookMarkStatusModificationRequest;
import com.bmi.bookmarkitapi.bookmark.domain.model.BookMark;
import com.bmi.bookmarkitapi.bookmark.domain.service.BookMarkCommandService;
import com.bmi.bookmarkitapi.bookmark.domain.service.BookMarkQueryService;
import com.bmi.bookmarkitapi.memberbookmark.application.model.MemberBookMarkTitleModificationRequest;
import com.bmi.bookmarkitapi.memberbookmark.domain.model.MemberBookmark;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookMarkStatusModificationService {
    private final BookMarkQueryService queryService;
    private final BookMarkCommandService commandService;
    public BookMark modify(
            Long id,
            BookMarkStatusModificationRequest request
    ) {

       BookMark bookMark = queryService.query(id);
       bookMark.statusModify(request.status);
       return commandService.save(bookMark);
    }


}
