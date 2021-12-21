package com.bmi.bookmarkitapi.bookmark.infrastructure;

import com.bmi.bookmarkitapi.bookmark.application.BookMarkCategorySettingService;
import com.bmi.bookmarkitapi.bookmark.application.BookMarkStatusModificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookMarkClassifiedEventHandler {
    private final BookMarkStatusModificationService bookMarkStatusModificationService;
    private final BookMarkCategorySettingService bookMarkCategorySettingService;

    @EventListener
    public void handleBookMarkClassifiedEvent(BookMarkClassifiedEvent event) {
        bookMarkStatusModificationService.complete(event.bookMarkId);
        bookMarkCategorySettingService.set(event.bookMarkId, event.category);
    }
}
