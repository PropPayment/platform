package com.proppay.platform.pay.adapter.in.web.dto;

import com.proppay.platform.pay.domain.exchange.Exchange;
import com.proppay.platform.pay.domain.exchange.ExchangeStatus;
import com.proppay.platform.pay.domain.lawyer.Lawyer;
import com.proppay.platform.pay.domain.user.User;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ExchangeDetailResponse {

    private Long exchangeId;         // 거래 ID
    private Long propertyId;         // 매물 ID
    private String buyerName;            // 구매자 이름
    private String sellerName;           // 판매자 이름

    @Builder.Default
    private String lawyerName = "미정";           // 법무사 이름

    private ExchangeStatus status;   // 거래 상태
    private LocalDateTime requestedAt;  // 요청 시간
    private String note;             // 거래 요청 메모 (선택 사항)

    public static ExchangeDetailResponse from(Exchange exchange, User Seller, User Buyer) {
        return ExchangeDetailResponse.builder()
                .exchangeId(exchange.getId())
                .propertyId(exchange.getPropertyId())
                .buyerName(Buyer.getUsername())
                .sellerName(Seller.getUsername())
                .status(exchange.getStatus())
                .requestedAt(exchange.getSnippet().getRequestedAt())
                .note(exchange.getSnippet().getNote())
                .build();
    }

    public static ExchangeDetailResponse from(Exchange exchange, User Seller, User Buyer, Lawyer Lawyer) {
        return ExchangeDetailResponse.builder()
                .exchangeId(exchange.getId())
                .propertyId(exchange.getPropertyId())
                .buyerName(Buyer.getUsername())
                .sellerName(Seller.getUsername())
                .lawyerName(Lawyer.getName())
                .status(exchange.getStatus())
                .requestedAt(exchange.getSnippet().getRequestedAt())
                .note(exchange.getSnippet().getNote())
                .build();
    }
}
