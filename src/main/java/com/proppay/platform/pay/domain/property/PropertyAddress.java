package com.proppay.platform.pay.domain.property;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class PropertyAddress {

    private String streetAddress; // 도로명 주소
    private String detailAddress; // 상세 주소 (동/호수 등)
    private String postalCode;    // 우편번호

    // 데이터의 검색 쉽게하는 용도
    private String dong;          // 동 (야탑동, 서현동 등 )
    private String district;      // 구/군 (강남구, 성남시 분당구 등)
    private String city;          // 도시 (서울, 경기 등)

    // 생성자
    public static PropertyAddress of(String streetAddress, String detailAddress,
                                     String postalCode) {
        return PropertyAddress.builder()
                .streetAddress(streetAddress)
                .detailAddress(detailAddress)
                .postalCode(postalCode)
                .build();
    }


}
