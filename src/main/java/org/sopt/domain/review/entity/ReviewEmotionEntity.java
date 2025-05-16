package org.sopt.domain.review.entity;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
class ReviewEmotionId implements Serializable {
    private Long reviewId;
    private Long emotionId;
}

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "review_emotion")
public class ReviewEmotionEntity {

    @EmbeddedId
    private ReviewEmotionId id;

    @MapsId("reviewId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id")
    private ReviewEntity review;

    @MapsId("emotionId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "emotion_id")
    private EmotionEntity emotion;

}
