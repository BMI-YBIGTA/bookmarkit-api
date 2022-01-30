package com.bmi.bookmarkitapi.bookmark.infrastructure;

import com.bmi.bookmarkitapi.bookmark.application.BookmarkCategorySettingService;
import com.bmi.bookmarkitapi.bookmark.application.BookmarkStatusModificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.StringTokenizer;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookmarkClassifiedEventConsumer {
    private final BookmarkStatusModificationService bookmarkStatusModificationService;
    private final BookmarkCategorySettingService bookmarkCategorySettingService;

    @RabbitListener(queues = "q.classification.classified")
    public void listen(String message) {
        StringTokenizer stringTokenizer = new StringTokenizer(message, "|||");

        Long bookmarkId = Long.parseLong(stringTokenizer.nextToken());
        String content = stringTokenizer.nextToken();
        String mainCategory = stringTokenizer.nextToken();
        String subCategory = stringTokenizer.nextToken();

        log.info("bookmarkId: " + bookmarkId);
        log.info("content: " + content);
        log.info("mainCategory: " + mainCategory);
        log.info("subCategory: " + subCategory);

        bookmarkStatusModificationService.complete(bookmarkId);
        bookmarkCategorySettingService.set(bookmarkId, content, mainCategory, subCategory);
    }
}
