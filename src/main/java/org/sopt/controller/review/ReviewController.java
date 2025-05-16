package org.sopt.controller.review;


import lombok.RequiredArgsConstructor;
import org.sopt.dto.book.response.BookResponseDTO;
import org.sopt.dto.review.response.ReviewListResponseDTO;
import org.sopt.dto.review.response.ReviewResponseDTO;
import org.sopt.global.dto.ResponseDTO;
import org.sopt.service.review.ReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("/reviews/{bookId}")
    public ResponseEntity<ResponseDTO<ReviewListResponseDTO>> getReviews(@PathVariable Long bookId) {
        return ResponseEntity.ok(ResponseDTO.success(reviewService.getReviewsByBookId(bookId)));
    }
}
