package com.bmi.bookmarkitapi.similarity.presentation;

import com.bmi.bookmarkitapi.common.dto.Response;
import com.bmi.bookmarkitapi.similarity.application.service.SimilarityRegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/similarity")
@RequiredArgsConstructor
public class SimilarityCommandController {

    private final SimilarityRegistrationService similarityRegistrationService;

    @PostMapping
    public Response.Empty register(@RequestBody String link) {
        similarityRegistrationService.register(link);
        return new Response.Empty();
    }
}
