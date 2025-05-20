package org.sopt.repository.Review;

import org.sopt.domain.review.entity.statistic.StarStatisticEntity;
import org.sopt.domain.review.mapper.ReviewMapper;
import org.sopt.domain.review.model.Emotion;
import org.sopt.domain.review.model.Review;
import org.sopt.domain.review.model.statistic.EmotionStatistic;
import org.sopt.domain.review.model.statistic.ReviewStatistic;
import org.sopt.domain.review.model.statistic.StarStatistic;
import org.sopt.service.review.ReviewStatisticService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public interface StarStatisticRepository extends JpaRepository<StarStatisticEntity,Long> {

}
