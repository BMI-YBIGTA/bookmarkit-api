package com.bmi.bookmarkitapi.bookmark.infrastructure;

import com.bmi.bookmarkitapi.bookmark.application.BookMarkCategorySettingService;
import com.bmi.bookmarkitapi.bookmark.application.BookMarkStatusModificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.StringTokenizer;

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
        System.out.println("bookmarkId: " + bookMarkId.toString());
        System.out.println("mainCategory: " + mainCategory);
        System.out.println("subCategory: " + subCategory);
        bookMarkStatusModificationService.complete(bookMarkId);
        bookMarkCategorySettingService.set(bookMarkId, mainCategory, subCategory);
    }
}
