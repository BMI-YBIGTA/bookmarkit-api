package com.bmi.bookmarkitapi.similarity.domain.service;

import com.bmi.bookmarkitapi.common.BaseCommandService;
import com.bmi.bookmarkitapi.similarity.domain.model.Similarity;
import com.bmi.bookmarkitapi.similarity.domain.repository.SimilarityRepository;
import org.springframework.stereotype.Service;

@Service
public class SimilarityCommandService extends BaseCommandService<Similarity> {

    public SimilarityCommandService(SimilarityRepository repository) {
        super(repository);
    }

    public void create(String inputLink, String outputLinks) {
        this.save(new Similarity(inputLink, outputLinks));
    }
}
