package com.bmi.bookmarkitapi.similarity.application;

import com.bmi.bookmarkitapi.similarity.application.model.response.SimilarityResponse;

public interface SimilarityService {

    SimilarityResponse getSimilarLinks(String link);
}
