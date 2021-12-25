package com.bmi.bookmarkitapi.similarity.application.service;

import com.bmi.bookmarkitapi.similarity.domain.service.SimilarityCommandService;
import com.bmi.bookmarkitapi.similarity.domain.service.SimilarityQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SimilarityRegistrationService {

    private final SimilarityQueryService similarityQueryService;
    private final SimilarityCommandService similarityCommandService;

    public void register(String inputLink) {
        if (similarityQueryService.query(inputLink).isEmpty()) {
            similarityCommandService.create(inputLink, null);
        }
    }
}
