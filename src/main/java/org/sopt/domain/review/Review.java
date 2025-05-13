package org.sopt.domain.review;

import jakarta.persistence.*;
import org.sopt.domain.book.Book;

@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    private String image;
    private Long star;
    private String date;

    @Enumerated(EnumType.STRING)
    private Empathy empathy;


    @ManyToOne
    @JoinColumn(name = "BOOK_ID")
    private Book book;

}
