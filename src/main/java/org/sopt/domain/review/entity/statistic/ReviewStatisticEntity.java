package org.sopt.domain.review.entity.statistic;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.sopt.domain.book.entity.BookEntity;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "review_statistic")
public class ReviewStatisticEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_statistic_id")
    private Long reviewStatisticId;

    private Double averageStar;

    @OneToOne
    @JoinColumn(name = "book_id", nullable = false, unique = true)
    private BookEntity book;

    @OneToMany(mappedBy = "reviewStatistic", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StarStatisticEntity> starStatisticEntities = new ArrayList<>();

    @OneToMany(mappedBy = "reviewStatistic", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EmotionStatisticEntity> emotionStatisticEntities = new ArrayList<>();
}
