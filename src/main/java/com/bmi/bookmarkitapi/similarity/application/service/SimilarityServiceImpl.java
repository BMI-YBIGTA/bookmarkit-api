package com.bmi.bookmarkitapi.similarity.application.service;

import com.bmi.bookmarkitapi.similarity.application.model.response.SimilarityResponse;
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
    public SimilarityResponse getSimilarLinks(String link) {
        // DB에 있으면 링크를 반환, 없으면 링크를 저장하고 모델 서버로 전송
        return similarityQueryService.findByInputLink(link)
                .map(SimilarityResponse::new)
                .orElseGet(() -> new SimilarityResponse(similarityCommandService.saveWithLinks(link)));
    }
}
