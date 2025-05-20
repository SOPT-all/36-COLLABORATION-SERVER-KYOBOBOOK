package org.sopt.repository.Review;

import org.sopt.domain.review.entity.ReviewEntity;
import org.sopt.domain.review.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<ReviewEntity, Long> {
    List<ReviewEntity> findAllByBook_BookId(Long bookId);

}
