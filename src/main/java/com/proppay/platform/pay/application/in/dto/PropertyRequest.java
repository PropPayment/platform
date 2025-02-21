package com.proppay.platform.pay.application.in.dto;

import com.proppay.platform.pay.domain.property.PropertyType;
import lombok.Data;

@Data
public class PropertyRequest {

    // 위치 정보
    private String streetAddress;
    private String detailAddress;
    private String postalCode;

    // 유저 정보
    private Long userId;

    // 원하는 가격
    private long price;

    // 매물의 특징
    private PropertyType type;
    private String title; // 매물 이름
    private String description; // 매물 설명
    private int quantity; // 방 개수
    private int bathrooms; // 욕실 개수 추가
    private int builtYear; // 건축 연도 추가


}
