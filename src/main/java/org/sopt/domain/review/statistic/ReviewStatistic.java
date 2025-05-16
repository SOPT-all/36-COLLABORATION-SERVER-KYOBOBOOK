package org.sopt.domain.review.statistic;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.sopt.domain.book.Book;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "review_statistic")
public class ReviewStatistic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewStatisticId;

    private Double averageStar;

    @OneToOne
    @JoinColumn(name = "book_id", nullable = false, unique = true)
    private Book book;

    @OneToMany(mappedBy = "reviewStatistic", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StarStatistic> starStatistics = new ArrayList<>();

    @OneToMany(mappedBy = "reviewStatistic", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EmotionStatistic> emotionStatistics = new ArrayList<>();
}
