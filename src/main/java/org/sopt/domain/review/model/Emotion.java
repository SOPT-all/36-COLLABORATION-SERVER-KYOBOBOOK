package org.sopt.domain.review.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Emotion {
    private final Long emotionId;
    private final String emotionTag;
}
