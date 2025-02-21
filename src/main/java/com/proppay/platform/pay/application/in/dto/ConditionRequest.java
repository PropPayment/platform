package com.proppay.platform.pay.application.in.dto;

import lombok.Data;

@Data
public class ConditionRequest {

    // 지역
    private String city; // 도시
    private String district; // 구별
    private String dong; // 동별

    // 가격
    private long price;

    // 방 개수
    private int quantity; // 방 개수
    private int bathrooms; // 욕실 개수 추가
    private int builtYear; // 건축 연도 추가

}
