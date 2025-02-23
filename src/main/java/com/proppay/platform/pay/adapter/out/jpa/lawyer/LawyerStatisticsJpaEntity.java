package com.proppay.platform.pay.adapter.out.jpa.lawyer;

import com.proppay.platform.pay.domain.lawyer.LawyerStatistics;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LawyerStatisticsJpaEntity {

    private double rating; // 평균 평점

    private long reviewCount; // 리뷰 개수

    private long likeCount;

    // from 메서드 (도메인 → JPA 엔티티)
    public static LawyerStatisticsJpaEntity from(LawyerStatistics statistics) {
        return LawyerStatisticsJpaEntity.builder()
                .rating(statistics.getRating())
                .reviewCount(statistics.getReviewCount())
                .likeCount(statistics.getLikeCount())
                .build();
    }

    // toDomain 메서드 (JPA 엔티티 → 도메인)
    public LawyerStatistics toDomain() {
        return LawyerStatistics.builder()
                .rating(rating)
                .reviewCount(reviewCount)
                .likeCount(likeCount)
                .build();
    }

}

