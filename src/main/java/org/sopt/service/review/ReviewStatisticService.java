package org.sopt.service.review;


import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.sopt.domain.review.entity.ReviewEntity;
import org.sopt.domain.review.entity.statistic.EmotionStatisticEntity;
import org.sopt.domain.review.entity.statistic.ReviewStatisticEntity;
import org.sopt.domain.review.entity.statistic.StarStatisticEntity;
import org.sopt.dto.review.response.EmotionStatisticDTO;
import org.sopt.dto.review.response.ReviewStatisticResponseDTO;
import org.sopt.dto.review.response.StarStatisticDTO;
import org.sopt.repository.Review.EmotionStatisticRepository;
import org.sopt.repository.Review.ReviewRepository;
import org.sopt.repository.Review.ReviewStatisticRepository;
import org.sopt.repository.Review.StarStatisticRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ReviewStatisticService {

    private final ReviewRepository reviewRepository;
    private final ReviewStatisticRepository reviewStatisticRepository;
    private final EmotionStatisticRepository emotionStatisticRepository;
    private final StarStatisticRepository starStatisticRepository;

    @Transactional(readOnly = true)
    public ReviewStatisticResponseDTO getReviewStatisticByBookId(Long bookId) {
        ReviewStatisticEntity reviewStatistic = reviewStatisticRepository
                .findWithStatsByBookId(bookId)
                .orElseGet(() -> {
                    calculateAndSaveStatistics(bookId);
                    return reviewStatisticRepository.findWithStatsByBookId(bookId)
                            .orElseThrow(() -> new EntityNotFoundException("리뷰 통계를 찾을 수 없습니다."));
                });

        return ReviewStatisticResponseDTO.builder()
                .averageStar(reviewStatistic.getAverageStar())
                .emotionStatistics(
                        reviewStatistic.getEmotionStatisticEntities().stream()
                                .map(es -> new EmotionStatisticDTO(es.getEmotionTag(), calculatePercentage(es.getCount(), reviewStatistic.getEmotionStatisticEntities())))
                                .toList())
                .starStatistics(
                        reviewStatistic.getStarStatisticEntities().stream()
                                .map(ss -> new StarStatisticDTO(ss.getStarPoint(), calculatePercentage(ss.getCount(), reviewStatistic.getStarStatisticEntities())))
                                .toList())
                .build();
    }

    private <T> float calculatePercentage(int count, List<T> list) {
        int total = list.stream()
                .mapToInt(e -> {
                    if (e instanceof EmotionStatisticEntity) {
                        return ((EmotionStatisticEntity) e).getCount();
                    } else if (e instanceof StarStatisticEntity) {
                        return ((StarStatisticEntity) e).getCount();
                    }
                    return 0;
                })
                .sum();
        return total == 0 ? 0f : (count * 100f / total);
    }

    @Transactional
    public void calculateAndSaveStatistics(Long bookId) {
        List<ReviewEntity> reviews = reviewRepository.findAllByBook_BookId(bookId);

        if (reviews.isEmpty()) {
            // 리뷰가 없으면 기존 통계 삭제 혹은 처리 로직 필요
            return;
        }

        // 평균 별점 계산
        double averageStar = reviews.stream()
                .mapToInt(ReviewEntity::getStarPoint)
                .average()
                .orElse(0.0);

        // 감정 통계 계산
        Map<String, Integer> emotionCountMap = new HashMap<>();
        for (ReviewEntity review : reviews) {
            review.getEmotionTags().forEach(tag ->
                    emotionCountMap.put(tag, emotionCountMap.getOrDefault(tag, 0) + 1));
        }

        // 별점 통계 계산
        Map<Integer, Integer> starCountMap = new HashMap<>();
        for (ReviewEntity review : reviews) {
            int star = review.getStarPoint();
            starCountMap.put(star, starCountMap.getOrDefault(star, 0) + 1);
        }

        // 기존 ReviewStatisticEntity 조회 혹은 새로 생성
        ReviewStatisticEntity reviewStatistic = reviewStatisticRepository.findWithStatsByBookId(bookId)
                .orElseGet(() -> {
                    ReviewStatisticEntity entity = new ReviewStatisticEntity();
                    try {
                        java.lang.reflect.Field bookField = ReviewStatisticEntity.class.getDeclaredField("book");
                        bookField.setAccessible(true);
                        // book 엔티티를 BookRepository에서 조회해 넣어야 함
                        // 예) bookField.set(entity, bookRepository.findById(bookId).orElseThrow());
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    return entity;
                });

        // 평균 별점 세팅
        try {
            java.lang.reflect.Field averageStarField = ReviewStatisticEntity.class.getDeclaredField("averageStar");
            averageStarField.setAccessible(true);
            averageStarField.set(reviewStatistic, averageStar);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // 기존 통계 초기화
        reviewStatistic.getEmotionStatisticEntities().clear();
        reviewStatistic.getStarStatisticEntities().clear();

        // 감정 통계 엔티티 생성 후 추가
        List<EmotionStatisticEntity> emotionStatistics = emotionCountMap.entrySet().stream()
                .map(entry -> {
                    EmotionStatisticEntity entity = new EmotionStatisticEntity();
                    try {
                        java.lang.reflect.Field tagField = EmotionStatisticEntity.class.getDeclaredField("emotionTag");
                        tagField.setAccessible(true);
                        tagField.set(entity, entry.getKey());

                        java.lang.reflect.Field countField = EmotionStatisticEntity.class.getDeclaredField("count");
                        countField.setAccessible(true);
                        countField.set(entity, entry.getValue());

                        java.lang.reflect.Field reviewStatField = EmotionStatisticEntity.class.getDeclaredField("reviewStatistic");
                        reviewStatField.setAccessible(true);
                        reviewStatField.set(entity, reviewStatistic);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    return entity;
                }).toList();

        // 별점 통계 엔티티 생성 후 추가
        List<StarStatisticEntity> starStatistics = starCountMap.entrySet().stream()
                .map(entry -> {
                    StarStatisticEntity entity = new StarStatisticEntity();
                    try {
                        java.lang.reflect.Field starPointField = StarStatisticEntity.class.getDeclaredField("starPoint");
                        starPointField.setAccessible(true);
                        starPointField.set(entity, entry.getKey());

                        java.lang.reflect.Field countField = StarStatisticEntity.class.getDeclaredField("count");
                        countField.setAccessible(true);
                        countField.set(entity, entry.getValue());

                        java.lang.reflect.Field reviewStatField = StarStatisticEntity.class.getDeclaredField("reviewStatistic");
                        reviewStatField.setAccessible(true);
                        reviewStatField.set(entity, reviewStatistic);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    return entity;
                }).toList();

        reviewStatistic.getEmotionStatisticEntities().addAll(emotionStatistics);
        reviewStatistic.getStarStatisticEntities().addAll(starStatistics);

        reviewStatisticRepository.save(reviewStatistic);
    }
}
