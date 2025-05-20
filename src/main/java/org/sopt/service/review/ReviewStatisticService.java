//package org.sopt.service.review;
//
//
//import jakarta.persistence.EntityNotFoundException;
//import lombok.RequiredArgsConstructor;
//import org.sopt.domain.book.entity.BookEntity;
//import org.sopt.domain.review.entity.ReviewEntity;
//import org.sopt.domain.review.entity.statistic.EmotionStatisticEntity;
//import org.sopt.domain.review.entity.statistic.ReviewStatisticEntity;
//import org.sopt.domain.review.entity.statistic.StarStatisticEntity;
//import org.sopt.domain.review.mapper.ReviewStatisticMapper;
//import org.sopt.dto.review.response.EmotionStatisticDTO;
//import org.sopt.dto.review.response.ReviewStatisticResponseDTO;
//import org.sopt.dto.review.response.StarStatisticDTO;
//import org.sopt.repository.Review.EmotionStatisticRepository;
//import org.sopt.repository.Review.ReviewRepository;
//import org.sopt.repository.Review.ReviewStatisticRepository;
//import org.sopt.repository.Review.StarStatisticRepository;
//import org.sopt.repository.book.BookRepository;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.function.Function;
//import java.util.stream.Collectors;
//import java.util.stream.IntStream;
//
//@Service
//@RequiredArgsConstructor
//public class ReviewStatisticService {
//
//    private final BookRepository bookRepository;
//    private final ReviewRepository reviewRepository;
//    private final ReviewStatisticRepository reviewStatisticRepository;
//    private final EmotionStatisticRepository emotionStatisticRepository;
//    private final StarStatisticRepository starStatisticRepository;
//
//    public ReviewStatisticResponseDTO updateReviewStatistics(Long bookId) {
//        // 1. 리뷰 불러오기
//        List<ReviewEntity> reviews = reviewRepository.findAllByBook_BookId(bookId);
//        if (reviews.isEmpty()) {
//            throw new RuntimeException("해당 책의 리뷰가 없습니다.");
//        }
//
//        // 총 리뷰 수
//        int totalReviews = reviews.size();
//
//        // 2. 평균 별점 계산
//        double averageStar = reviews.stream()
//                .mapToInt(r -> r.getStar().intValue())
//                .average()
//                .orElse(0.0);
//
//        // 3. 별점별 개수 계산
//        Map<Integer, Long> starCounts = reviews.stream()
//                .collect(Collectors.groupingBy(r -> r.getStar().intValue(), Collectors.counting()));
//
//        // 4. 감정별 개수 계산
//        Map<String, Long> emotionCounts = reviews.stream()
//                .flatMap(r -> r.getReviewEmotionEntities().stream())
//                .map(re -> re.getEmotion().getEmotionTag())
//                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
//
//        // 감정 총 개수 (전체 감정 태그 갯수 합)
//        long totalEmotions = emotionCounts.values().stream().mapToLong(Long::longValue).sum();
//
//        // 5. 기존 ReviewStatisticEntity 가져오기 또는 새로 생성
//        ReviewStatisticEntity stat = reviewStatisticRepository.findByBook_BookId(bookId)
//                .orElseGet(() -> {
//                    BookEntity book = bookRepository.findById(bookId)
//                            .orElseThrow(() -> new RuntimeException("책이 존재하지 않습니다."));
//                    return ReviewStatisticEntity.builder()
//                            .book(book)
//                            .averageStar(0.0)
//                            .build();
//                });
//
//        // 6. 평균 별점 업데이트
//        stat.updateAverageStar(averageStar);
//
//        // 7. 새로운 StarStatisticEntity 리스트 생성 (count는 유지)
//        List<StarStatisticEntity> newStarStats = IntStream.rangeClosed(1, 5)
//                .mapToObj(star -> StarStatisticEntity.builder()
//                        .starPoint(star)
//                        .count(starCounts.getOrDefault(star, 0L).intValue())
//                        .reviewStatistic(stat)
//                        .build())
//                .collect(Collectors.toList());
//
//        // 8. 새로운 EmotionStatisticEntity 리스트 생성 (count는 유지)
//        List<EmotionStatisticEntity> newEmotionStats = emotionCounts.entrySet().stream()
//                .map(entry -> EmotionStatisticEntity.builder()
//                        .emotionTag(entry.getKey())
//                        .count(entry.getValue().intValue())
//                        .reviewStatistic(stat)
//                        .build())
//                .collect(Collectors.toList());
//
//        // 9. 기존 리스트 교체
//        stat.updateStarStatistics(newStarStats);
//        stat.updateEmotionStatistics(newEmotionStats);
//
//        // 10. 저장
//        reviewStatisticRepository.save(stat);
//
//        // 11. DTO 생성 시 count → percentage 변환해서 반환
//        return new ReviewStatisticResponseDTO(
//                averageStar,
//                newEmotionStats.stream()
//                        .map(es -> new EmotionStatisticDTO(
//                                es.getEmotionTag(),
//                                (int) Math.round((es.getCount() * 100.0) / totalReviews)
//                        ))
//                        .toList(),
//                newStarStats.stream()
//                        .map(ss -> new StarStatisticDTO(
//                                ss.getStarPoint(),
//                                (int) Math.round((ss.getCount() * 100.0) / totalReviews)
//                        ))
//                        .toList()
//        );
//
//    }
//
//
//
//}
