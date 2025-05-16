package org.sopt.domain.review.entity.statistic;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "star_statistic")
public class StarStatisticEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "star_statistic_id")
    private Long starStatisticId;

    private int starPoint; // 1~5
    private int count;

    @ManyToOne
    @JoinColumn(name = "review_statistic_id")
    private ReviewStatisticEntity reviewStatistic;
}
