package org.sopt.domain.review.mapper;

import org.sopt.domain.review.entity.EmotionEntity;
import org.sopt.domain.review.model.Emotion;

public class EmotionMapper {
    public static Emotion toDomain(EmotionEntity entity) {
        return new Emotion(
                entity.getEmotionId(),
                entity.getEmotionTag()
        );
    }
}
