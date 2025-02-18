package com.proppay.platform.pay.domain.property;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class PropertyStatistic {

    private long viewCount;
    private long likeCount;

    // 생성자
    public static PropertyStatistic of(long viewCount, long likeCount) {
        return PropertyStatistic.builder()
                .viewCount(viewCount)
                .likeCount(likeCount)
                .build();
    }


}
