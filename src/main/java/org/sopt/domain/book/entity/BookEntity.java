package org.sopt.domain.book.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.sopt.domain.review.entity.ReviewEntity;
import org.sopt.domain.review.entity.statistic.ReviewStatisticEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "book")
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Long bookId;
    private String title;
    private String price;
    private String publisher;
    private String bookIndex;
    private String description;
    private LocalDate publishDate;
    private String author;
    private Long ranking;
    private String imageUrl;


    @OneToOne(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    private ReviewStatisticEntity reviewStatisticEntity;

    @OneToMany(mappedBy = "book")
    private List<ReviewEntity> reviews =new ArrayList<>();
}
