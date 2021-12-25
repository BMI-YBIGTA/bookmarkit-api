package com.bmi.bookmarkitapi.similarity.domain.repository;

import com.bmi.bookmarkitapi.common.BaseRepository;
import com.bmi.bookmarkitapi.similarity.domain.model.Similarity;

import java.util.Optional;

public interface SimilarityRepository extends BaseRepository<Similarity> {

    Optional<Similarity> findByInputLink(String inputLink);
}
