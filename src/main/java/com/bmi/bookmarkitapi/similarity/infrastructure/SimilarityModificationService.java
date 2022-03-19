package com.bmi.bookmarkitapi.similarity.infrastructure;

import com.bmi.bookmarkitapi.common.exception.ResourceNotFoundException;
import com.bmi.bookmarkitapi.similarity.domain.model.Similarity;
import com.bmi.bookmarkitapi.similarity.domain.service.SimilarityQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class SimilarityModificationService {

    private final SimilarityQueryService similarityQueryService;

    public void setOutputLinks(String inputLink, String outputLinks) {
        Similarity similarity = similarityQueryService.findByInputLink(inputLink)
                .orElseThrow(() -> new ResourceNotFoundException("유사 링크 추천을 위한 링크가 저장되지 않았습니다. inputLink=" + inputLink));
        similarity.setOutputLinks(outputLinks);
    }
}
