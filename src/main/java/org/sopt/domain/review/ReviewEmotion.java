package org.sopt.domain.review;


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
public class ReviewEmotion {

    @EmbeddedId
    private ReviewEmotionId id;

    @MapsId("reviewId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id")
    private Review review;

    @MapsId("emotionId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "emotion_id")
    private Emotion emotion;

}

//@Getter
//@Entity
//@IdClass(ReviewEmotionId.class)
//@Data
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
//@Table(name = "review_emotion")
//public class ReviewEmotion {
//
//    @Id
//    @Column(name = "review_id")
//    private Long reviewId;
//
//    @Id
//    @Column(name = "emotion_id")
//    private Long emotionId;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "review_id")
//    private Review review;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "emotion_id")
//    private Emotion emotion;


