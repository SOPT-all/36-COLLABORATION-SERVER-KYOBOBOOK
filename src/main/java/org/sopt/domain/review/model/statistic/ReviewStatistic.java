package org.sopt.domain.review.model.statistic;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ReviewStatistic {
    private final Long reviewStatisticId;
    private final Double averageStar;
    private final List<StarStatistic> starStatistics;
    private final List<EmotionStatistic> emotionStatistics;
}
