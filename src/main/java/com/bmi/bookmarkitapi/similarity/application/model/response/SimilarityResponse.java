package com.bmi.bookmarkitapi.similarity.application.model.response;

import com.bmi.bookmarkitapi.similarity.domain.model.Similarity;
import lombok.Getter;

import java.util.List;

@Getter
public class SimilarityResponse {
    public String inputLink;
    public List<String> outputLinks;

    public SimilarityResponse(Similarity similarity) {
        inputLink = similarity.getInputLink();
        outputLinks = similarity.outputLinksToList(similarity.getOutputLinks());
    }
}
