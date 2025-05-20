package org.sopt.repository.Review;

import org.sopt.domain.review.entity.statistic.StarStatisticEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public interface StarStatisticRepository extends JpaRepository<StarStatisticEntity,Long> {

}
