package com.proppay.platform.pay.domain.exchange;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class ExchangeSnippet {

    private LocalDateTime requestedAt; // 거래 요청 시간
    private LocalDateTime confirmedAt; // 거래 확정 시간
    private LocalDateTime completedAt; // 거래 완료 시간

    private String note;

    // 생성자
    public static ExchangeSnippet of(LocalDateTime requestedAt,String note) {
        return ExchangeSnippet.builder()
                .requestedAt(requestedAt)
                .confirmedAt(requestedAt)
                .completedAt(requestedAt)
                .note(note)
                .build();
    }

}
