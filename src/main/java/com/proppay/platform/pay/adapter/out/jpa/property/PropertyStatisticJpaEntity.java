package com.proppay.platform.pay.adapter.out.jpa.property;

import com.proppay.platform.pay.domain.property.PropertyStatistic;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Embeddable
public class PropertyStatisticJpaEntity {

    private long viewCount;
    private long likeCount;


    // from
    public static PropertyStatisticJpaEntity from(PropertyStatistic statistic) {
        return PropertyStatisticJpaEntity.builder()
                .viewCount(statistic.getViewCount())
                .likeCount(statistic.getLikeCount())
                .build();
    }

    // toDomain
    public PropertyStatistic toDomain() {
        return PropertyStatistic.builder()
                .viewCount(viewCount)
                .likeCount(likeCount)
                .build();
    }

}
