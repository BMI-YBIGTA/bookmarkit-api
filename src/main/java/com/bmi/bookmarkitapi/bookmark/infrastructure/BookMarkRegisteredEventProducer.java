package com.bmi.bookmarkitapi.bookmark.infrastructure;

import com.bmi.bookmarkitapi.bookmark.domain.event.BookMarkRegisteredEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookMarkRegisteredEventProducer {
    private final RabbitTemplate rabbitTemplate;

    public void produce(BookMarkRegisteredEvent event) {
        rabbitTemplate.convertAndSend("q.classification.registered", event.toString());
    }
}
