package com.bmi.bookmarkitapi.similarity.infrastructure;

import com.bmi.bookmarkitapi.similarity.domain.service.SimilarityCommandService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class SimilarityAnalyzedEventConsumer {

    private final SimilarityCommandService similarityCommandService;

    /**
     * @param message "inputLink|||outputLink1|||outputLink2|||outputLink3"
     */
    @RabbitListener(queues = "q.similarity.analyzed")
    public void listen(String message) {
        String delimiter = "|||";

        String inputLink = message.substring(0, message.indexOf(delimiter));
        String outputLinks = message.substring(message.indexOf(delimiter) + delimiter.length());

        log.info("inputLink: " + inputLink);
        log.info("outputLinks: " + outputLinks);

        similarityCommandService.create(inputLink, outputLinks);
    }
}
