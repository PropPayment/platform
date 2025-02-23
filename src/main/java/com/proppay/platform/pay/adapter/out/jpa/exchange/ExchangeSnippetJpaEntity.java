package com.proppay.platform.pay.adapter.out.jpa.exchange;

import com.proppay.platform.pay.domain.exchange.ExchangeSnippet;
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
public class ExchangeSnippetJpaEntity {

    private LocalDateTime requestedAt; // 거래 요청 시간
    private LocalDateTime confirmedAt; // 거래 확정 시간
    private LocalDateTime completedAt; // 거래 완료 시간

    private String note;

    // from
    public static ExchangeSnippetJpaEntity from(ExchangeSnippet snippet) {
        return ExchangeSnippetJpaEntity.builder()
                .requestedAt(snippet.getRequestedAt())
                .confirmedAt(snippet.getConfirmedAt())
                .completedAt(snippet.getCompletedAt())
                .note(snippet.getNote())
                .build();
    }

    // toDomain
    public ExchangeSnippet toDomain() {
        return ExchangeSnippet.builder()
                .requestedAt(requestedAt)
                .confirmedAt(confirmedAt)
                .completedAt(completedAt)
                .note(note)
                .build();
    }
}
