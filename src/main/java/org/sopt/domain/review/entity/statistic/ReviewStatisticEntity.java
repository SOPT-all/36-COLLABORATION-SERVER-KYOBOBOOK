package org.sopt.domain.review.entity.statistic;

import jakarta.persistence.*;
import lombok.*;
import org.sopt.domain.book.entity.BookEntity;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Entity
@Table(name = "review_statistic")
public class ReviewStatisticEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_statistic_id")
    private Long reviewStatisticId;

    private Double averageStar;
    @Column(name = "average_emotion")
    private String averageEmotion;



    @OneToOne
    @JoinColumn(name = "book_id", nullable = false, unique = true)
    private BookEntity book;

    @OneToMany(mappedBy = "reviewStatistic", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StarStatisticEntity> starStatisticEntities = new ArrayList<>();

    @OneToMany(mappedBy = "reviewStatistic", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EmotionStatisticEntity> emotionStatisticEntities = new ArrayList<>();

    public void updateAverageStar(Double averageStar) {
        this.averageStar = averageStar;
    }
    public void updateBook(BookEntity book) {
        this.book = book;
    }
    public void updateStarStatistics(List<StarStatisticEntity> starStats) {
        if (this.starStatisticEntities == null) {
            this.starStatisticEntities = new ArrayList<>();
        }
        this.starStatisticEntities.clear();
        this.starStatisticEntities.addAll(starStats);
    }

    public void updateEmotionStatistics(List<EmotionStatisticEntity> emotionStats) {
        if (this.emotionStatisticEntities == null) {
            this.emotionStatisticEntities = new ArrayList<>();
        }
        this.emotionStatisticEntities.clear();
        this.emotionStatisticEntities.addAll(emotionStats);
    }


}
