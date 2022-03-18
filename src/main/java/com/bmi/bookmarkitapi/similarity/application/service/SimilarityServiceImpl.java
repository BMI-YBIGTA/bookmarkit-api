package com.bmi.bookmarkitapi.similarity.application.service;

import com.bmi.bookmarkitapi.common.exception.ResourceNotFoundException;
import com.bmi.bookmarkitapi.similarity.application.model.response.SimilarityResponse;
import com.bmi.bookmarkitapi.similarity.domain.model.Similarity;
import com.bmi.bookmarkitapi.similarity.domain.service.SimilarityCommandService;
import com.bmi.bookmarkitapi.similarity.domain.service.SimilarityQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SimilarityServiceImpl implements SimilarityService {

    private final SimilarityCommandService similarityCommandService;
    private final SimilarityQueryService similarityQueryService;

    @Override
    public void register(String inputLink) {
        if (similarityQueryService.findByInputLink(inputLink).isEmpty()) {
            similarityCommandService.saveWithLinks(inputLink);
        }
    }

    @Override
    public SimilarityResponse getSimilarLinks(String link) {
        Similarity similarity = similarityQueryService.findByInputLink(link)
                .orElseThrow(ResourceNotFoundException::new);
        return new SimilarityResponse(similarity);
    }
}
