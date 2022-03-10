package com.bmi.bookmarkitapi.similarity.domain.model;

import com.bmi.bookmarkitapi.common.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Similarity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String inputLink;
    private String outputLinks;

    public Similarity(String inputLink, String outputLinks) {
        this.inputLink = inputLink;
        this.outputLinks = outputLinks;
    }

    public List<String> outputLinksToList(String outputLinks) {
        if (outputLinks == null) {
            return Collections.emptyList();
        }
        return Arrays.asList(outputLinks.split("|||"));
    }

    @PostPersist
    public void publishSimilarityRegisteredEvent() {
        registerEvent(inputLink);
    }
}
