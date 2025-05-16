package org.sopt.dto.review.response;

import java.util.List;

public record ReviewListResponseDTO (int reviewCounts,List<ReviewResponseDTO> reviewResponseDTOList){
}
