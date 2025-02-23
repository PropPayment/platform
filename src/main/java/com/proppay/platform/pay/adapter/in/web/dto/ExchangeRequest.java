package com.proppay.platform.pay.adapter.in.web.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ExchangeRequest {

    @NotNull(message = "구매자 ID는 필수입니다.")
    private Long buyerId;

    @NotNull(message = "판매자 ID는 필수입니다.")
    private Long sellerId;

    @NotNull(message = "매물 ID는 필수입니다.")
    private Long propertyId;

    private Long lawyerId;

    @NotNull(message = "거래 요청 시간은 필수입니다.")
    private LocalDateTime requestedAt; // 거래 요청 시간

    private String note; // 부가 사항

//    private boolean nonFaceToFace; // 비대면 여부
}
