package org.sopt.domain.review.mapper;
import org.sopt.domain.book.mapper.BookMapper;
import org.sopt.domain.book.model.Book;
import org.sopt.domain.review.entity.ReviewEntity;
import org.sopt.domain.review.model.Emotion;
import org.sopt.domain.review.model.Review;
import org.sopt.dto.review.response.ReviewResponseDTO;

import java.util.List;

public class ReviewMapper {
public static Review toDomain(ReviewEntity reviewEntity){
    return new Review(
            reviewEntity.getReviewId(),
            reviewEntity.getReviewer(),
            reviewEntity.getContent(),
            reviewEntity.getReviewImage(),
            reviewEntity.getStar(),
            reviewEntity.getDate(),
            BookMapper.toDomain(reviewEntity.getBook()),
            reviewEntity.getReviewEmotionEntities().stream()
                    .map(reviewEmotion -> EmotionMapper.toDomain(reviewEmotion.getEmotion()))
                    .toList()
    );


}

    public static ReviewResponseDTO toDto(Review review) {
        List<String> emotionTags = review.getEmotions().stream()
                .map(Emotion::getEmotionTag)
                .toList();

        return new ReviewResponseDTO(
                review.getReviewer(),
                review.getContent(),
                review.getStar(),
                review.getReviewImage(),
                review.getDate(),
                emotionTags
        );
}}
