package com.proppay.platform.pay.domain.lawyer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class LawyerStatistics {

    private double rating; // 평균 평점

    private long reviewCount; // 리뷰 개수

    private long likeCount;

    // 생성자
    public static LawyerStatistics of(double rating, long reviewCount, long likeCount) {
        return LawyerStatistics.builder()
                .rating(rating)
                .reviewCount(reviewCount)
                .likeCount(likeCount)
                .build();
    }
}
