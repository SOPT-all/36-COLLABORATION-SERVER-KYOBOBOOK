package org.sopt.domain.review.entity.statistic;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    @ManyToOne
    @JoinColumn(name = "review_statistic_id")
    private ReviewStatisticEntity reviewStatistic;
}
