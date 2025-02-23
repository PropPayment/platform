package com.proppay.platform.pay.domain.property;

import com.proppay.platform.pay.domain.BaseDomain;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class Property extends BaseDomain {

    private Long id;
    private Long ownerId;

    private PropertyType type;
    private PropertyAddress address;
    private PropertySnippet snippet;
    private PropertyStatistic statistic;

    private long price;
    private boolean verified;

    // 기존 아파트와 연관되는 변수
    private String kaptCode;

    // 생성자
    public static Property of(Long ownerId, PropertyType type, PropertyAddress address, PropertySnippet snippet, PropertyStatistic statistic, long price, String kaptCode) {
        return Property.builder()
                .ownerId(ownerId)
                .kaptCode(kaptCode)
                .type(type)
                .address(address)
                .snippet(snippet)
                .statistic(statistic)
                .price(price)
                .verified(false)
                .build();
    }

}
