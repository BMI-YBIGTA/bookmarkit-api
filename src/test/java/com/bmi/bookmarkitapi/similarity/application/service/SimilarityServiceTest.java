package com.bmi.bookmarkitapi.similarity.application.service;

import com.bmi.bookmarkitapi.similarity.application.model.response.SimilarityResponse;
import com.bmi.bookmarkitapi.similarity.domain.model.Similarity;
import com.bmi.bookmarkitapi.similarity.domain.service.SimilarityCommandService;
import com.bmi.bookmarkitapi.similarity.domain.service.SimilarityQueryService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SimilarityServiceTest {

    @Mock
    SimilarityCommandService similarityCommandService;
    @Mock
    SimilarityQueryService similarityQueryService;
    @InjectMocks
    SimilarityServiceImpl similarityService;

    @DisplayName("DB에 링크가 없으면 링크를 저장하고 모델 서버로 전송하는데, 분석이 완료되지 않았으면 유사 링크가 빈 리스트로 반환된다")
    @Test
    void getSimilarLinks1() {
        String link = "https://blog.com/";

        when(similarityQueryService.findByInputLink(link)).thenReturn(Optional.empty());
        when(similarityCommandService.saveWithLinks(link)).thenReturn(new Similarity(link));

        SimilarityResponse result = similarityService.getSimilarLinks(link);

        assertThat(result.inputLink).isEqualTo(link);
        assertThat(result.outputLinks.size()).isEqualTo(0);
        verify(similarityQueryService).findByInputLink(link);
        verify(similarityCommandService).saveWithLinks(link);
    }

    @DisplayName("DB에 링크가 존재하면 링크를 반환하는데, 분석이 완료되었으면 유사 링크 3개가 반환된다")
    @Test
    void getSimilarLinks2() {
        String link = "https://blog1.com/";
        String outputLink1 = "https://blog2.com";
        String outputLink2 = "https://blog3.com";
        String outputLink3 = "https://blog4.com";
        String outputLinks = String.join("|||", outputLink1, outputLink2, outputLink3);

        Similarity similarity = new Similarity(link);
        similarity.setOutputLinks(outputLinks);
        when(similarityQueryService.findByInputLink(link)).thenReturn(Optional.of(similarity));

        SimilarityResponse result = similarityService.getSimilarLinks(link);

        assertThat(result.inputLink).isEqualTo(link);
        assertThat(result.outputLinks.size()).isEqualTo(3);
        assertThat(result.outputLinks).containsExactly(outputLink1, outputLink2, outputLink3);
        verify(similarityQueryService).findByInputLink(link);
    }
}
