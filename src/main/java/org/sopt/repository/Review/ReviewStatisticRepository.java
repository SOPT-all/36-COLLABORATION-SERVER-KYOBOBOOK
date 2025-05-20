package org.sopt.repository.Review;

import org.sopt.domain.review.entity.ReviewEntity;
import org.sopt.domain.review.entity.statistic.ReviewStatisticEntity;
import org.sopt.domain.review.model.statistic.ReviewStatistic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ReviewStatisticRepository extends JpaRepository<ReviewStatisticEntity, Long> {
    //Optional<ReviewStatistic> findByBookId(Long bookId);


    //fetch join을 사용하는 메서드(JPQL)
    @Query("SELECT rs FROM ReviewStatistic rs " +
            "LEFT JOIN FETCH rs.emotionStatistics " +
            "LEFT JOIN FETCH rs.starStatistics " +
            "WHERE rs.book.id = :bookId")
    Optional<ReviewStatistic> findWithStatsByBookId(@Param("bookId") Long bookId);
}
