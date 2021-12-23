package com.bmi.bookmarkitapi.bookmark.infrastructure;

import com.bmi.bookmarkitapi.bookmark.application.BookMarkCategorySettingService;
import com.bmi.bookmarkitapi.bookmark.application.BookMarkStatusModificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.StringTokenizer;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookMarkClassifiedEventConsumer {
    private final BookMarkStatusModificationService bookMarkStatusModificationService;
    private final BookMarkCategorySettingService bookMarkCategorySettingService;

    @RabbitListener(queues = "q.classification.classified")
    public void listen(String message) {
        StringTokenizer stringTokenizer = new StringTokenizer(message, "|||");

        Long bookMarkId = Long.parseLong(stringTokenizer.nextToken());
        String mainCategory = stringTokenizer.nextToken();
        String subCategory = stringTokenizer.nextToken();

        log.info("bookmarkId: " + bookMarkId);
        log.info("mainCategory: " + mainCategory);
        log.info("subCategory: " + subCategory);

        bookMarkStatusModificationService.complete(bookMarkId);
        bookMarkCategorySettingService.set(bookMarkId, mainCategory, subCategory);
    }
}
