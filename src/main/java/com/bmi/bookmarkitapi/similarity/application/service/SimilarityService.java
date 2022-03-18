package com.bmi.bookmarkitapi.similarity.application.service;

import com.bmi.bookmarkitapi.similarity.application.model.response.SimilarityResponse;

public interface SimilarityService {

    SimilarityResponse getSimilarLinks(String link);

    void register(String link);
}
