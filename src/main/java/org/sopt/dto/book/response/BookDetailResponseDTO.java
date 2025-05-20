package org.sopt.dto.book.response;

import java.time.LocalDate;
import java.util.List;

public record BookDetailResponseDTO(
        String title,
        String author,
        String publisher,
        String date,
        String price,
        String description,
        String imageUrl,
        List<String> bookIndex
) {}
