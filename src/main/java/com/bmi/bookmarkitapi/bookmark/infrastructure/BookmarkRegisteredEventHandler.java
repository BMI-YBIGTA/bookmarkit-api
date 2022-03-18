package com.bmi.bookmarkitapi.bookmark.infrastructure;

import com.bmi.bookmarkitapi.bookmark.domain.event.BookmarkRegisteredEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class BookmarkRegisteredEventHandler {

    private final BookmarkModificationService bookmarkModificationService;
    private final BookmarkRegisteredEventProducer bookmarkRegisteredEventProducer;

    @EventListener
    public void handleBookmarkRegisteredEvent(BookmarkRegisteredEvent event) {
        log.info("event: " + event.toString());
        bookmarkModificationService.request(event.bookmarkId);
        bookmarkRegisteredEventProducer.produce(event);
    }
}
