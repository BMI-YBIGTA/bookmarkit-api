package com.bmi.bookmarkitapi.similarity.application.model;

import com.bmi.bookmarkitapi.similarity.domain.model.Similarity;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

public class SimilarityDto {

    @Getter
    @AllArgsConstructor
    public static class Response {
        public String inputLink;
        public List<String> outputLinks;

        public static Response from(Similarity similarity) {
            return new Response(
                    similarity.getInputLink(),
                    similarity.outputLinksToList(similarity.getOutputLinks())
            );
        }
    }
}
