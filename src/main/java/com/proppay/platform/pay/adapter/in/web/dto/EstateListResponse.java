package com.proppay.platform.pay.adapter.in.web.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.proppay.platform.pay.domain.estate.Estate;
import lombok.Builder;
import lombok.Data;

import java.util.*;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)  // NULL 값인 필드는 JSON 응답에서
public class EstateListResponse {

    // 아파트 기본 정보
    private String complexCode;  // 아파트 단지 코드
    private String complexName;  // 아파트 단지명
    private String address;      // 주소

    // 매물 개수
    private Integer propertyCount;

    // 좌표
    private EstateLocationResponse location;


    public static EstateListResponse from(Estate estate) {
        return EstateListResponse.builder()
                .complexCode(estate.getKaptCode())
                .complexName(estate.getKaptName())
                .address(estate.getKaptAddr())
                .location(EstateLocationResponse.from(estate))
                .build();
    }

    public static EstateListResponse from(Estate estate, int count) {
        return EstateListResponse.builder()
                .complexCode(estate.getKaptCode())
                .complexName(estate.getKaptName())
                .address(estate.getKaptAddr())
                .propertyCount(count > 0 ? count : null) // count가 0이면 JSON에서 제외
                .location(EstateLocationResponse.from(estate))
                .build();
    }

    public static List<EstateListResponse> from(List<Estate> estates) {
        return estates.stream().map(EstateListResponse::from).toList();
    }



}
