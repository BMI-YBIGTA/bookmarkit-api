package com.bmi.bookmarkitapi.bookmark.application;


import com.bmi.bookmarkitapi.bookmark.application.model.BookMarkStatusModificationRequest;
import com.bmi.bookmarkitapi.bookmark.domain.model.BookMark;
import com.bmi.bookmarkitapi.bookmark.domain.model.BookMarkStatus;
import com.bmi.bookmarkitapi.bookmark.domain.service.BookMarkCommandService;
import com.bmi.bookmarkitapi.bookmark.domain.service.BookMarkQueryService;
import com.bmi.bookmarkitapi.memberbookmark.application.model.BookMarkPassingRequest;
import com.bmi.bookmarkitapi.memberbookmark.application.model.BookMarkPassingResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookMarkRegistrationService {
    private final BookMarkCommandService commandService;
    private final BookMarkStatusModificationService statusModificationService;


    public BookMarkPassingResponse register(BookMarkPassingRequest bookMarkPassingRequest){

        //카테고리가 null인 상태로 bookmark 등록
        BookMark bookMark =commandService.create(bookMarkPassingRequest.header,
                bookMarkPassingRequest.link,
                bookMarkPassingRequest.content);

        bookMark = statusModificationService
                .modify(bookMark.getId(), new BookMarkStatusModificationRequest(BookMarkStatus.REQUESTING));

        //Classification 서비스를 이용하여 카테고리 받기.
        String category = "";

        bookMark.setCategory(category);
        bookMark = commandService.save(bookMark);


        bookMark = statusModificationService
                .modify(bookMark.getId(), new BookMarkStatusModificationRequest(BookMarkStatus.REGISTERED));

        // 그후 response로 반환한다.
        return new BookMarkPassingResponse(bookMark.getId());

    }
}
