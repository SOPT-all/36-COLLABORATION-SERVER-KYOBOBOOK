package org.sopt.domain.review.entity.statistic;


import jakarta.persistence.*;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "emotion_statistic")
public class EmotionStatisticEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "emotion_statistic_id")
    private Long emotionStatisticId;

    private String emotionTag;

    private int count;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_statistic_id")
    private ReviewStatisticEntity reviewStatistic;

    @Builder
    public EmotionStatisticEntity(String emotionTag, int count, ReviewStatisticEntity reviewStatistic) {
        this.emotionTag = emotionTag;
        this.count = count;
        this.reviewStatistic = reviewStatistic;
    }

    public void changeEmotion(String emotionTag, int count) {
        this.emotionTag = emotionTag;
        this.count = count;
    }

    public void registerReviewStatistic(ReviewStatisticEntity reviewStatistic) {
        this.reviewStatistic = reviewStatistic;
    }
}

//
//@Getter
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
//@AllArgsConstructor
//@Builder
//@Entity
//@Table(name = "emotion_statistic")
//public class EmotionStatisticEntity {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "emotion_statistic_id")
//    private Long emotionStatisticId;
//
//    private String emotionTag;
//    private int count;
//
//    @ManyToOne
//    @JoinColumn(name = "review_statistic_id")
//    private ReviewStatisticEntity reviewStatistic;
//
//    @Builder
//    public EmotionStatisticEntity(String emotionTag, int count, ReviewStatisticEntity reviewStatistic) {
//        this.emotionTag = emotionTag;
//        this.count = count;
//        this.reviewStatistic = reviewStatistic;
//    }
//
//
//    public void updateEmotionTag(String emotionTag) {
//        this.emotionTag = emotionTag;
//    }
//
//    public void updateCount(int count) {
//        this.count = count;
//    }
//
//    public void updateReviewStatistic(ReviewStatisticEntity reviewStatistic) {
//        this.reviewStatistic = reviewStatistic;
//    }
//}
