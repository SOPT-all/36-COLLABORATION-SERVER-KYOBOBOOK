package org.sopt.repository.Review;

import org.sopt.domain.review.entity.statistic.EmotionStatisticEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmotionStatisticRepository extends JpaRepository<EmotionStatisticEntity, Long> {
}
