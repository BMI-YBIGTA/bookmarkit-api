package com.bmi.bookmarkitapi.bookmark.infrastructure;

import com.bmi.bookmarkitapi.bookmark.application.BookMarkStatusModificationService;
import com.bmi.bookmarkitapi.bookmark.domain.event.BookMarkRegisteredEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookMarkRegisteredEventHandler {
    private final BookMarkStatusModificationService bookMarkStatusModificationService;

    @EventListener
    public void handleBookMarkRegisteredEvent(BookMarkRegisteredEvent event) {
        System.out.println("test");
        System.out.println(event.bookMarkId);
        bookMarkStatusModificationService.request(event.bookMarkId);
        //TODO: RabbitMQ 메시징
    }
}
