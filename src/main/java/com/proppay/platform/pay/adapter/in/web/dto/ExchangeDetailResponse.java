package com.proppay.platform.pay.adapter.in.web.dto;

import com.proppay.platform.pay.domain.exchange.Exchange;
import com.proppay.platform.pay.domain.exchange.ExchangeStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ExchangeDetailResponse {

    private Long exchangeId;         // 거래 ID
    private Long propertyId;         // 매물 ID
    private String buyerName;            // 구매자 ID
    private String sellerName;           // 판매자 ID
    private ExchangeStatus status;   // 거래 상태
    private LocalDateTime requestedAt;  // 요청 시간
    private String note;             // 거래 요청 메모 (선택 사항)

    public static ExchangeDetailResponse from(Exchange exchange) {
        return ExchangeDetailResponse.builder()
                .exchangeId(exchange.getId())
                .propertyId(exchange.getProperty().getId())
                .buyerName(exchange.getBuyer().getUsername())
                .sellerName(exchange.getSeller().getUsername())
                .status(exchange.getStatus())
                .requestedAt(exchange.getSnippet().getRequestedAt())
                .note(exchange.getSnippet().getNote())
                .build();
    }
}
