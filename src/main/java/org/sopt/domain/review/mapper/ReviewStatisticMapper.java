package org.sopt.domain.review.mapper;

import org.sopt.domain.review.entity.statistic.ReviewStatisticEntity;
import org.sopt.domain.review.entity.statistic.StarStatisticEntity;
import org.sopt.domain.review.entity.statistic.EmotionStatisticEntity;
import org.sopt.dto.review.response.ReviewStatisticResponseDTO;
import org.sopt.dto.review.response.StarStatisticDTO;
import org.sopt.dto.review.response.EmotionStatisticDTO;

import java.util.Map;
import java.util.stream.Collectors;

public class ReviewStatisticMapper {
    public static ReviewStatisticResponseDTO toDto(ReviewStatisticEntity entity) {
        Map<Integer, Integer> starDistribution = entity.getStarStatisticEntities().stream()
                .collect(Collectors.toMap(
                        StarStatisticEntity::getStarPoint,
                        StarStatisticEntity::getCount
                ));

        Map<String, Integer> emotionDistribution = entity.getEmotionStatisticEntities().stream()
                .collect(Collectors.toMap(
                        EmotionStatisticEntity::getEmotionTag,
                        EmotionStatisticEntity::getCount
                ));

        return new ReviewStatisticResponseDTO(
                entity.getAverageStar(),
                starDistribution,
                emotionDistribution
        );
    }




    public static StarStatisticDTO toStarStatisticDto(StarStatisticEntity entity) {
        return new StarStatisticDTO(entity.getStarPoint(), entity.getCount());
    }

    public static EmotionStatisticDTO toEmotionStatisticDto(EmotionStatisticEntity entity) {
        return new EmotionStatisticDTO(entity.getEmotionTag(), entity.getCount());
    }
}
