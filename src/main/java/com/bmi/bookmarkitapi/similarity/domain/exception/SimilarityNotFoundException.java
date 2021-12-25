package com.bmi.bookmarkitapi.similarity.domain.exception;

import com.bmi.bookmarkitapi.common.exception.NotFoundException;

public class SimilarityNotFoundException extends NotFoundException {

    public SimilarityNotFoundException() {
        super("해당 유사도가 존재하지 않습니다.");
    }
}
