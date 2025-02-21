package com.proppay.platform.pay.adapter.in.web.dto;

import com.proppay.platform.pay.domain.property.PropertyStatistic;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PropertyStatisticsResponse {
    private long viewCount;
    private long likeCount;

    public static PropertyStatisticsResponse from(PropertyStatistic statistic) {
        return PropertyStatisticsResponse.builder()
                .viewCount(statistic.getViewCount())
                .likeCount(statistic.getLikeCount())
                .build();
    }
}
