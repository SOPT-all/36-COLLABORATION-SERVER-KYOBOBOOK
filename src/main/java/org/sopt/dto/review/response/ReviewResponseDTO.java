package org.sopt.dto.review.response;

import org.sopt.domain.review.model.Emotion;

import java.time.LocalDate;
import java.util.List;

public record ReviewResponseDTO(String reviewer, String content, Long star, String reviewImage, String date,
                                List<String> emotionTags) {
}


