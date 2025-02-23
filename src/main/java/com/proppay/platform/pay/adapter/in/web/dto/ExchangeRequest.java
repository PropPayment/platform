package com.proppay.platform.pay.adapter.in.web.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ExchangeRequest {

    private Long buyerId;
    private Long sellerId;
    private Long propertyId;

    private LocalDateTime requestedAt; // 거래 요청 시간
    private String note; // 부가 사항

    private boolean nonFaceToFace; // 비대면 여부
}
