package com.bmi.bookmarkitapi.bookmark.infrastructure;

import com.bmi.bookmarkitapi.bookmark.domain.event.BookmarkRegisteredEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BookmarkRegisteredEventProducer {

    private final RabbitTemplate rabbitTemplate;

    public void produce(BookmarkRegisteredEvent event) {
        rabbitTemplate.convertAndSend("q.classification.registered", event.toString());
    }
}
