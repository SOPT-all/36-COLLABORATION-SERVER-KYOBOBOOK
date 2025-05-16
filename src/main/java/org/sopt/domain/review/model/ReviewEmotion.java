package org.sopt.domain.review.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ReviewEmotion {
    private final Long reviewId;
    private final Long emotionId;
}