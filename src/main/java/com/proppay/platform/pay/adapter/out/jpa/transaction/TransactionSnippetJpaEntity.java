package com.proppay.platform.pay.adapter.out.jpa.transaction;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Embeddable
public class TransactionSnippetJpaEntity {

    private LocalDateTime requestedAt; // 거래 요청 시간
    private LocalDateTime confirmedAt; // 거래 확정 시간
    private LocalDateTime completedAt; // 거래 완료 시간

    private long price;
    private String note;

}
