package org.sopt.dto.review.response;

import java.util.List;

public record BookDetailResponseDTO(
        Long bookid,
        String title,
        String author,
        String publisher,
        String date,
        String price,
        String description,
        List<String> bookIndex
) {}
