package org.sopt.domain.review.entity.statistic;

import jakarta.persistence.*;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
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

    public void updateStarPoint(int starPoint) {
        this.starPoint = starPoint;
    }

    public void updateCount(int count) {
        this.count = count;
    }

    public void updateReviewStatistic(ReviewStatisticEntity reviewStatistic) {
        this.reviewStatistic = reviewStatistic;
    }

}
