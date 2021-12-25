package com.bmi.bookmarkitapi.similarity.presentation;

import com.bmi.bookmarkitapi.common.dto.Response;
import com.bmi.bookmarkitapi.similarity.application.model.SimilarityDto;
import com.bmi.bookmarkitapi.similarity.domain.model.Similarity;
import com.bmi.bookmarkitapi.similarity.domain.service.SimilarityQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/similarity")
@RequiredArgsConstructor
public class SimilarityQueryController {

    private final SimilarityQueryService similarityQueryService;

    @GetMapping
    public Response.Item<SimilarityDto.Response> get(@RequestParam String link) {
        Optional<Similarity> similarity = similarityQueryService.query(link);

        if (similarity.isEmpty()) {
            return new Response.Item<>(null);
        }
        return new Response.Item<>(SimilarityDto.Response.from(similarity.get()));
    }
}
