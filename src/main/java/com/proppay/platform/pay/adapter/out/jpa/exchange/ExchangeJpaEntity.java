package com.proppay.platform.pay.adapter.out.jpa.exchange;

import com.proppay.platform.pay.domain.exchange.Exchange;
import com.proppay.platform.pay.domain.exchange.ExchangeStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter@Entity
public class ExchangeJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long sellerId;

    private Long buyerId;

    private Long propertyId; // 변경된 부분 (Property 객체 대신 ID만 저장)

    private Long lawyerId; // 비어도 되는건가

    @Embedded
    private ExchangeSnippetJpaEntity snippet;

    @Enumerated(EnumType.STRING)
    private ExchangeStatus status;

    // from
    public static ExchangeJpaEntity from(Exchange exchange) {
        return ExchangeJpaEntity.builder()
                .id(exchange.getId())
                .sellerId(exchange.getSellerId())
                .buyerId(exchange.getBuyerId())
                .propertyId(exchange.getPropertyId()) // 변경된 부분
                .lawyerId(exchange.getLawyerId())
                .snippet(ExchangeSnippetJpaEntity.from(exchange.getSnippet()))
                .status(exchange.getStatus())
                .build();
    }

    // toDomain
    public Exchange toDomain() {
        return Exchange.builder()
                .id(id)
                .sellerId(sellerId)
                .buyerId(buyerId)
                .propertyId(propertyId)
                .lawyerId(lawyerId)
                .snippet(snippet.toDomain())
                .status(status)
                .build();
    }

}
