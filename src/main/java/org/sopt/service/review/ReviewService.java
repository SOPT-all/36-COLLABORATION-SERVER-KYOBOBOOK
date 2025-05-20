package org.sopt.service.review;


import jakarta.persistence.EntityNotFoundException;
import org.sopt.domain.review.entity.ReviewEntity;
import org.sopt.domain.review.mapper.ReviewMapper;
import org.sopt.domain.review.model.Review;
import org.sopt.domain.review.model.statistic.ReviewStatistic;
import org.sopt.dto.review.response.ReviewListResponseDTO;
import org.sopt.dto.review.response.ReviewResponseDTO;
import org.sopt.dto.review.response.ReviewStatisticResponseDTO;
import org.sopt.global.exception.BusinessException;
import org.sopt.repository.Review.ReviewRepository;
import org.sopt.repository.Review.ReviewStatisticRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReviewService {

    private ReviewRepository reviewRepository;
    private ReviewStatisticRepository reviewStatisticRepository;

    public ReviewService(ReviewRepository reviewRepository){
        this.reviewRepository = reviewRepository;
    }


    public ReviewListResponseDTO  getReviewsByBookId(Long bookId) {

        // 1. 엔티티 -> 도메인 변환 (예: ReviewEntity -> Review)
        List<Review> reviewList = reviewRepository.findAllByBook_BookId(bookId)
                .stream()
                .map(ReviewMapper::toDomain)
                .toList();

        List<ReviewResponseDTO> reviewResponseList = reviewList.stream() //리뷰 도메인을 인자로 받아서, dto로 변환하도록, toDTO매섣를 mapper에 정의
                .map(ReviewMapper::toDto)
                .toList();

        return new ReviewListResponseDTO(reviewResponseList.size(), reviewResponseList);

    }

    public ReviewStatisticResponseDTO getReviewStatisticByBookId(Long bookId){
        ReviewStatistic reviewStatistic = reviewStatisticRepository.findWithStatsByBookId(bookId).orElseThrow(()-> new EntityNotFoundException("리뷰 통계를 찾을 수 없습니다."));

        return;

    }
}