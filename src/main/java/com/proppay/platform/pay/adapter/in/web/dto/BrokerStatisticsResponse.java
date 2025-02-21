package com.proppay.platform.pay.adapter.in.web.dto;

import com.proppay.platform.pay.domain.broker.BrokerStatistics;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BrokerStatisticsResponse {

    private Long likeCount;       // 좋아요 수
    private Double reviewScore;   // 평균 리뷰 점수
    private Long reviewCount;     // 리뷰 개수

    public static BrokerStatisticsResponse from(BrokerStatistics statistics) {
        return BrokerStatisticsResponse.builder()
                .likeCount(statistics.getLikeCount())
                .reviewScore(statistics.getReviewScore())
                .reviewCount(statistics.getReviewCount())
                .build();
    }
}

