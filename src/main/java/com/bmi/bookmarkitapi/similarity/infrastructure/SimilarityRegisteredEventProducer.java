package com.bmi.bookmarkitapi.similarity.infrastructure;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class SimilarityRegisteredEventProducer {

    private final RabbitTemplate rabbitTemplate;

    public void produce(String inputLink) {
        rabbitTemplate.convertAndSend("q.similarity.registered", inputLink);
    }
}
