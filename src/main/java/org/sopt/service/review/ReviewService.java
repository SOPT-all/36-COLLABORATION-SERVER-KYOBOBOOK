package org.sopt.service.review;


import jakarta.persistence.EntityNotFoundException;
import org.sopt.domain.review.entity.ReviewEntity;
import org.sopt.domain.review.entity.statistic.ReviewStatisticEntity;
import org.sopt.domain.review.entity.statistic.EmotionStatisticEntity;
import org.sopt.domain.review.entity.statistic.StarStatisticEntity;
import org.sopt.domain.review.mapper.ReviewMapper;
import org.sopt.domain.review.model.Review;
import org.sopt.domain.review.model.statistic.ReviewStatistic;
import org.sopt.dto.review.response.*;
import org.sopt.global.exception.BusinessException;
import org.sopt.repository.Review.ReviewRepository;
import org.sopt.repository.Review.ReviewStatisticRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ReviewService {

        private ReviewRepository reviewRepository;
        private ReviewStatisticRepository reviewStatisticRepository;

        public ReviewService(ReviewRepository reviewRepository,ReviewStatisticRepository reviewStatisticRepository){
            this.reviewRepository = reviewRepository;
            this.reviewStatisticRepository = reviewStatisticRepository;
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
    public ReviewStatisticResponseDTO getReviewStatistic(Long bookId) {
        ReviewStatisticEntity stat = reviewStatisticRepository.findByBook_BookId(bookId)
                .orElseThrow(() -> new RuntimeException("해당 책의 리뷰 통계가 없습니다."));

        Double averageStar = stat.getAverageStar();

        Map<Integer, Integer> starDistribution = stat.getStarStatisticEntities().stream()
                .collect(Collectors.toMap(
                        StarStatisticEntity::getStarPoint,
                        StarStatisticEntity::getCount
                ));

        Map<String, Integer> emotionDistribution = stat.getEmotionStatisticEntities().stream()
                .collect(Collectors.toMap(
                        EmotionStatisticEntity::getEmotionTag,
                        EmotionStatisticEntity::getCount
                ));

        return new ReviewStatisticResponseDTO(averageStar, starDistribution, emotionDistribution);
    }



}