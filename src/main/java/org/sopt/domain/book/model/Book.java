package org.sopt.domain.book.model;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class Book {
    private Long bookId;
    private String title;
    private String price;
    private String publisher;
    private String bookIndex;
    private String description;
    private String publishDate;
    private String author;
    private Long ranking;
    private String imageUrl;
}
