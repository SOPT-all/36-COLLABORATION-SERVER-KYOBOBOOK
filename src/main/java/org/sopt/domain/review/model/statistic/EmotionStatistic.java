package org.sopt.domain.review.model.statistic;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class EmotionStatistic {
    private final Long emotionStatisticId;
    private final String emotionTag;
    private final int count;
}
