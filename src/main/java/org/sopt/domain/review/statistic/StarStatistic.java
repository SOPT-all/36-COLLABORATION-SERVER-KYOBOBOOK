package org.sopt.domain.review.statistic;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "star_statistic")
public class StarStatistic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long starStatisticId;

    private int starPoint; // 1~5
    private int count;

    @ManyToOne
    @JoinColumn(name = "review_statistic_id")
    private ReviewStatistic reviewStatistic;
}
