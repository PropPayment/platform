package com.proppay.platform.pay.domain.transaction;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class TransactionSnippet {

    private LocalDateTime requestedAt; // 거래 요청 시간
    private LocalDateTime confirmedAt; // 거래 확정 시간
    private LocalDateTime completedAt; // 거래 완료 시간

    private long price;
    private String note;

    // 생성자
    public static TransactionSnippet of(LocalDateTime requestedAt, long price, String note) {
        return TransactionSnippet.builder()
                .requestedAt(requestedAt)
                .price(price)
                .note(note)
                .build();
    }

}
