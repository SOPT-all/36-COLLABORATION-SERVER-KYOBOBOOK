package org.sopt.domain.book;

import jakarta.persistence.*;
import org.sopt.domain.review.Review;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String price;
    private String publisher;
    private String bookIndex;
    private String description;
    private LocalDate date;
    private String author;

    @OneToMany(mappedBy = "book")
    private List<Review> reviews;
}
