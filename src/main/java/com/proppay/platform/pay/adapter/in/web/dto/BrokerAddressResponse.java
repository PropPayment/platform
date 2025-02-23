package com.proppay.platform.pay.adapter.in.web.dto;

import com.proppay.platform.pay.domain.broker.BrokerAddress;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BrokerAddressResponse {

    private String streetAddress; // 도로명 주소
    private String detailAddress; // 상세 주소 (동/호수 등)
    private String postalCode;    // 우편번호

    public static BrokerAddressResponse from(BrokerAddress address) {
        return BrokerAddressResponse.builder()
                .streetAddress(address.getStreetAddress())
                .detailAddress(address.getDetailAddress())
                .postalCode(address.getPostalCode())
                .build();
    }
}


