package com.bmi.bookmarkitapi.similarity.presentation;

import com.bmi.bookmarkitapi.common.dto.Response;
import com.bmi.bookmarkitapi.similarity.application.model.response.SimilarityResponse;
import com.bmi.bookmarkitapi.similarity.application.service.SimilarityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/similarity")
@RestController
public class SimilarityController {

    private final SimilarityService similarityService;

    @GetMapping
    public Response.Item<SimilarityResponse> getSimilarLinks(@RequestParam String link) {
        return new Response.Item<>(similarityService.getSimilarLinks(link));
    }
}
