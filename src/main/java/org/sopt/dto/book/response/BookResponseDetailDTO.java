package org.sopt.dto.book.response;

import java.time.LocalDate;
import java.util.List;

public record BookResponseDetailDTO(
        String title,
        String author,
        String publisher,
        LocalDate date,
        String price,
        String description,
        List<String> bookIndex) {
}
