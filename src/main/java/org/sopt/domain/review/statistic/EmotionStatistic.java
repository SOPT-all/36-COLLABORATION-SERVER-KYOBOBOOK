package org.sopt.domain.review.statistic;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "emotion_statistic")
public class EmotionStatistic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long emotionStatisticId;

    private String emotionTag;
    private int count;

    @ManyToOne
    @JoinColumn(name = "review_statistic_id")
    private ReviewStatistic reviewStatistic;
}
