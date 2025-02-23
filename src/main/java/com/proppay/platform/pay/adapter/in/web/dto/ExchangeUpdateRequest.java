package com.proppay.platform.pay.adapter.in.web.dto;

import com.proppay.platform.pay.domain.exchange.ExchangeStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ExchangeUpdateRequest {

    @NotNull(message = "거래 ID는 필수입니다.")
    private Long exchangeId;         // 필수

    @NotNull(message = "거래 상태는 필수입니다.")
    private ExchangeStatus status;   // 필수

    private LocalDateTime requestedAt; // 선택 (null이면 기존 값 유지)
}
