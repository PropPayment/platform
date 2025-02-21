package com.proppay.platform.pay.adapter.out.jpa.broker;

import com.proppay.platform.pay.domain.broker.BrokerAddress;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Embeddable
public class BrokerAddressJpaEntity {

    private String streetAddress; // 도로명 주소
    private String detailAddress; // 상세 주소 (동/호수 등)
    private String postalCode;    // 우편번호

    // 데이터의 검색 쉽게하는 용도
    private String city;          // 도시 (서울, 부산 등)
    private String district;      // 구/군 (강남구, 수원시 영통구 등)

    // from
    public static BrokerAddressJpaEntity from(BrokerAddress brokerAddress) {
        return BrokerAddressJpaEntity.builder()
                .streetAddress(brokerAddress.getStreetAddress())
                .detailAddress(brokerAddress.getDetailAddress())
                .postalCode(brokerAddress.getPostalCode())
                .city(brokerAddress.getCity())
                .district(brokerAddress.getDistrict())
                .build();
    }

    // toDomain
    public BrokerAddress toDomain() {
        return BrokerAddress.builder()
                .streetAddress(streetAddress)
                .detailAddress(detailAddress)
                .postalCode(postalCode)
                .city(city)
                .district(district)
                .build();
    }
}
