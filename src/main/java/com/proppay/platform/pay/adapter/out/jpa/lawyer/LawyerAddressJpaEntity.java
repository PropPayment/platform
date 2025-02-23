package com.proppay.platform.pay.adapter.out.jpa.lawyer;

import com.proppay.platform.pay.domain.lawyer.LawyerAddress;
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
public class LawyerAddressJpaEntity {

    private String streetAddress; // 도로명 주소
    private String detailAddress; // 상세 주소 (동/호수 등)
    private String postalCode;    // 우편번호

    // 데이터의 검색 쉽게하는 용도
    private String city;          // 도시 (서울, 부산 등)
    private String district;      // 구/군 (강남구, 수원시 영통구 등)
    private String dong;

    // from
    public static LawyerAddressJpaEntity from(LawyerAddress address) {
        return LawyerAddressJpaEntity.builder()
                .streetAddress(address.getStreetAddress())
                .detailAddress(address.getDetailAddress())
                .postalCode(address.getPostalCode())
                .city(address.getCity())
                .district(address.getDistrict())
                .dong(address.getDong())
                .build();
    }

    // toDomain
    public LawyerAddress toDomain() {
        return LawyerAddress.builder()
                .streetAddress(streetAddress)
                .detailAddress(detailAddress)
                .postalCode(postalCode)
                .city(city)
                .district(district)
                .build();
    }
}
