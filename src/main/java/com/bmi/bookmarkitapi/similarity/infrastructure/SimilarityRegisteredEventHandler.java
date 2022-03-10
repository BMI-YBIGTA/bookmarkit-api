package com.bmi.bookmarkitapi.similarity.infrastructure;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class SimilarityRegisteredEventHandler {

    private final SimilarityRegisteredEventProducer similarityRegisteredEventProducer;

    @EventListener
    public void handleSimilarityRegisteredEvent(String inputLink) {
        log.info("event: " + inputLink);
        similarityRegisteredEventProducer.produce(inputLink);
    }
}
