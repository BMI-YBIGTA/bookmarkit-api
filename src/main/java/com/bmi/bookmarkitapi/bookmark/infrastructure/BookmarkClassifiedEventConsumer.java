package com.bmi.bookmarkitapi.bookmark.infrastructure;

import com.bmi.bookmarkitapi.bookmark.application.BookmarkModificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.StringTokenizer;

@Slf4j
@RequiredArgsConstructor
@Service
public class BookmarkClassifiedEventConsumer {

    private final BookmarkModificationService bookmarkModificationService;

    @RabbitListener(queues = "q.classification.classified")
    public void listen(String message) {
        StringTokenizer stringTokenizer = new StringTokenizer(message, "|");

        Long bookmarkId = Long.parseLong(stringTokenizer.nextToken());
        String content = stringTokenizer.nextToken();
        String mainCategory = stringTokenizer.nextToken();
        String subCategory = stringTokenizer.nextToken();

        log.info("bookmarkId: " + bookmarkId);
        log.info("content: " + content);
        log.info("mainCategory: " + mainCategory);
        log.info("subCategory: " + subCategory);

        bookmarkModificationService.setContentAndCategory(bookmarkId, content, mainCategory, subCategory);
        bookmarkModificationService.complete(bookmarkId);
    }
}
