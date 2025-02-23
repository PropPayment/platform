package com.proppay.platform.pay.adapter.out.jpa.exchange;

import com.proppay.platform.pay.adapter.out.jpa.lawyer.LawyerJpaEntity;
import com.proppay.platform.pay.adapter.out.jpa.property.PropertyJpaEntity;
import com.proppay.platform.pay.adapter.out.jpa.user.UserJpaEntity;
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
@Getter
@Entity
public class ExchangeJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private UserJpaEntity seller;

    @ManyToOne(fetch = FetchType.LAZY)
    private UserJpaEntity buyer;

    @ManyToOne(fetch = FetchType.LAZY)
    private LawyerJpaEntity lawyer;

    @OneToOne(fetch = FetchType.LAZY)
    private PropertyJpaEntity property;

    @Embedded
    private ExchangeSnippetJpaEntity snippet;

    @Enumerated(EnumType.STRING)
    private ExchangeStatus status;

    // from
    public static ExchangeJpaEntity from(Exchange exchange) {
        return ExchangeJpaEntity.builder()
                .id(exchange.getId())
                .seller(UserJpaEntity.from(exchange.getSeller()))
                .buyer(UserJpaEntity.from(exchange.getBuyer()))
                .lawyer(LawyerJpaEntity.from(exchange.getLawyer()))
                .property(PropertyJpaEntity.from(exchange.getProperty()))
                .snippet(ExchangeSnippetJpaEntity.from(exchange.getSnippet()))
                .status(exchange.getStatus())
                .build();
    }

    // toDomain
    public Exchange toDomain() {
        return Exchange.builder()
                .id(id)
                .seller(seller.toDomain())
                .buyer(buyer.toDomain())
                .lawyer(lawyer.toDomain())
                .property(property.toDomain())
                .snippet(snippet.toDomain())
                .status(status)
                .build();
    }

}
