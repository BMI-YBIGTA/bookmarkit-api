package com.bmi.bookmarkitapi.bookmark.infrastructure;

import com.bmi.bookmarkitapi.bookmark.application.BookMarkStatusModificationService;
import com.bmi.bookmarkitapi.bookmark.domain.event.BookMarkRegisteredEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class BookMarkRegisteredEventHandler {
    private final BookMarkStatusModificationService bookMarkStatusModificationService;
    private final BookMarkRegisteredEventProducer bookMarkRegisteredEventProducer;

    @EventListener
    public void handleBookMarkRegisteredEvent(BookMarkRegisteredEvent event) {
        log.info("event: " + event.toString());
        bookMarkStatusModificationService.request(event.bookMarkId);
        bookMarkRegisteredEventProducer.produce(event);
    }
}
