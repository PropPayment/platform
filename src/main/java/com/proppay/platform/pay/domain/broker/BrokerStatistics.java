package com.proppay.platform.pay.domain.broker;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class BrokerStatistics {

    private long likeCount = 0;  // 좋아요 수
    private double reviewScore = 0.0;  // 평균 리뷰 점수
    private long reviewCount = 0;  // 리뷰 개수

    // 생성자
    public static BrokerStatistics of(long likeCount, double reviewScore, long reviewCount) {
        return BrokerStatistics.builder()
                .likeCount(likeCount)
                .reviewScore(reviewScore)
                .reviewCount(reviewCount)
                .build();
    }

}
