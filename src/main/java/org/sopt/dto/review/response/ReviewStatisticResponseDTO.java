package org.sopt.dto.review.response;

import java.util.List;

public record ReviewStatisticResponseDTO(Double averageStar,
                                         List<EmotionStatisticDTO> emotionStatistics,
                                         List<StarStatisticDTO> starStatistics) {
}
