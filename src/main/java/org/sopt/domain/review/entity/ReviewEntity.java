package org.sopt.domain.review.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.sopt.domain.book.entity.BookEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "review")
public class ReviewEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long reviewId;
    private String reviewer;
    private String content;
    private String reviewImage;
    private Long star;
    private String date;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private BookEntity book;

    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReviewEmotionEntity> reviewEmotionEntities = new ArrayList<>();


}
