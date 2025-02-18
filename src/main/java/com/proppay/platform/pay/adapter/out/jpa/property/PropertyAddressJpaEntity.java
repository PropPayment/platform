package com.proppay.platform.pay.adapter.out.jpa.property;

import com.proppay.platform.pay.domain.property.PropertyAddress;
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
public class PropertyAddressJpaEntity {

    private String streetAddress; // 도로명 주소
    private String detailAddress; // 상세 주소 (동/호수 등)
    private String postalCode;    // 우편번호

    // 데이터의 검색 쉽게하는 용도
    private String city;          // 도시 (서울, 부산 등)
    private String district;      // 구/군 (강남구, 수원시 영통구 등)

    // from
    public static PropertyAddressJpaEntity from(PropertyAddress propertyAddress) {
        return PropertyAddressJpaEntity.builder()
                .streetAddress(propertyAddress.getStreetAddress())
                .detailAddress(propertyAddress.getDetailAddress())
                .postalCode(propertyAddress.getPostalCode())
                .city(propertyAddress.getCity())
                .district(propertyAddress.getDistrict())
                .build();
    }

    // toDomain
    public PropertyAddress toDomain() {
        return PropertyAddress.builder()
                .streetAddress(streetAddress)
                .detailAddress(detailAddress)
                .postalCode(postalCode)
                .city(city)
                .district(district)
                .build();
    }
}
