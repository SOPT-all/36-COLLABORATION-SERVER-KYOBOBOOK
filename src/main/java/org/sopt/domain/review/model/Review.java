package org.sopt.domain.review.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.sopt.domain.book.model.Book;

import java.time.LocalDate;
import java.util.List;

@Getter
@AllArgsConstructor
public class Review {
    private final Long reviewId;
    private final String reviewer;
    private final String content;
    private final String reviewImage;
    private final Long star;
    private final String date;
    private final Book book;
    private final List<Emotion> emotions;
}
