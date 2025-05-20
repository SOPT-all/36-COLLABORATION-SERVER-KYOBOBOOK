package org.sopt.dto.review.response;

import java.util.List;
import java.util.Map;

public record ReviewStatisticResponseDTO(
        Double averageStar,
        Map<Integer, Integer> starDistribution,
        Map<String, Integer> emotionDistribution

) {}
