package com.proppay.platform.pay.domain.property;

import com.proppay.platform.pay.domain.BaseDomain;
import com.proppay.platform.pay.domain.user.User;
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
    private User owner;

    private PropertyType type;
    private PropertyAddress address;
    private PropertySnippet snippet;
    private PropertyStatistic statistic;

    private long price;
    private boolean verified;

    // 생성자
    public static Property of(User user, PropertyType type, PropertyAddress address, PropertySnippet snippet, PropertyStatistic statistic, long price) {
        return Property.builder()
                .owner(user)
                .type(type)
                .address(address)
                .snippet(snippet)
                .statistic(statistic)
                .price(price)
                .verified(false)
                .build();
    }

}
