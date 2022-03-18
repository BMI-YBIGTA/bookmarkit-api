package com.bmi.bookmarkitapi.similarity.application.service;

import com.bmi.bookmarkitapi.similarity.application.model.response.SimilarityResponse;

public interface SimilarityService {

    void register(String link);

    SimilarityResponse getSimilarLinks(String link);
}
