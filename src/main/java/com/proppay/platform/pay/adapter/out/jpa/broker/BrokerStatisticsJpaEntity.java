package com.proppay.platform.pay.adapter.out.jpa.broker;

import com.proppay.platform.pay.domain.broker.BrokerStatistics;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BrokerStatisticsJpaEntity {

    private long likeCount = 0;  // 좋아요 수
    private double reviewScore = 0.0;  // 평균 리뷰 점수
    private long reviewCount = 0;  // 리뷰 개수

    // from
    public static BrokerStatisticsJpaEntity from(BrokerStatistics statistics) {
        return BrokerStatisticsJpaEntity.builder()
                .likeCount(statistics.getLikeCount())
                .reviewScore(statistics.getReviewScore())
                .reviewCount(statistics.getReviewCount())
                .build();
    }

    // toDomain
    public BrokerStatistics toDomain(){
        return BrokerStatistics.of(this.likeCount, this.reviewScore, this.reviewCount);
    }


}
