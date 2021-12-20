package com.bmi.bookmarkitapi.bookmark.application;


import com.bmi.bookmarkitapi.bookmark.domain.model.BookMark;
import com.bmi.bookmarkitapi.bookmark.domain.service.BookMarkCommandService;
import com.bmi.bookmarkitapi.bookmark.domain.service.BookMarkQueryService;
import com.bmi.bookmarkitapi.memberbookmark.application.model.BookMarkPassingRequest;
import com.bmi.bookmarkitapi.memberbookmark.application.model.BookMarkPassingResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookMarkQueryOrCreateService {
    private final BookMarkQueryService queryService;
    private final BookMarkCommandService commandService;


    //반환 타입??
    public Boolean urlQuery(BookMarkPassingRequest bookMarkPassingRequest){

        return queryService.urlQuery(bookMarkPassingRequest.link).isPresent();
    }


    public BookMarkPassingResponse register(BookMarkPassingRequest bookMarkPassingRequest){

        //분류 서비스 실행시키고, 받은 값으로 bookmark command 서비스 실행
        String category="";

        BookMark bookMark =commandService.create(bookMarkPassingRequest.header,
                bookMarkPassingRequest.link,
                bookMarkPassingRequest.content,
                 category);

        // 그후 response로 반환한다.
        return new BookMarkPassingResponse(bookMark.getId());

    }
}
