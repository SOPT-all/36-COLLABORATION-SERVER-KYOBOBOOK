package org.sopt.domain.review.model.statistic;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class StarStatistic {
    private final Long starStatisticId;
    private final int starPoint;
    private final int count;
}
