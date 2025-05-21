package org.sopt.repository.Review;

import org.sopt.domain.review.entity.statistic.ReviewStatisticEntity;

import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

public interface ReviewStatisticRepository extends JpaRepository<ReviewStatisticEntity, Long> {
    Optional<ReviewStatisticEntity> findByBook_BookId(Long bookId);



//    //fetch join을 사용하는 메서드(JPQL)
//    @Query("SELECT rs FROM ReviewStatistic rs " +
//            "LEFT JOIN FETCH rs.emotionStatistics " +
//            "LEFT JOIN FETCH rs.starStatistics " +
//            "WHERE rs.book.id = :bookId")
//    Optional<ReviewStatisticEntity> findWithStatsByBookId(@Param("bookId") Long bookId);
}
