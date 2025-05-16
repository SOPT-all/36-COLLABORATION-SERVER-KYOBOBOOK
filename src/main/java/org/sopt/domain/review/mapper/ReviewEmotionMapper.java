package org.sopt.domain.review.mapper;

import org.sopt.domain.review.entity.ReviewEmotionEntity;
import org.sopt.domain.review.model.ReviewEmotion;

public class ReviewEmotionMapper {
    public static ReviewEmotion toDomain(ReviewEmotionEntity entity) {

        return new ReviewEmotion(
                entity.getReview().getReviewId(),
                entity.getEmotion().getEmotionId()
        );
    }
}
