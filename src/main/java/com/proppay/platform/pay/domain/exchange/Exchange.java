package com.proppay.platform.pay.domain.exchange;

import com.proppay.platform.pay.domain.broker.Broker;
import com.proppay.platform.pay.domain.property.Property;
import com.proppay.platform.pay.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class Exchange {

    private Long id;

    private User seller;
    private User buyer;
    private Broker broker;

    private Property property;

    private ExchangeSnippet snippet;
    private ExchangeStatus status;

    // 생성자
    public static Exchange of(User seller, User buyer, Property property,
                              ExchangeSnippet snippet) {
        return Exchange.builder()
                .seller(seller)
                .buyer(buyer)
                .property(property)
                .snippet(snippet)
                .status(ExchangeStatus.REQUESTED) // 기본값: 대기 중
                .build();
    }

}
