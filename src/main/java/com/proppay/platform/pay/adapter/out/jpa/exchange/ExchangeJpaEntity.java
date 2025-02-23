package com.proppay.platform.pay.adapter.out.jpa.exchange;

import com.proppay.platform.pay.adapter.out.jpa.broker.BrokerJpaEntity;
import com.proppay.platform.pay.adapter.out.jpa.property.PropertyJpaEntity;
import com.proppay.platform.pay.adapter.out.jpa.user.UserJpaEntity;
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
    private BrokerJpaEntity broker;

    @OneToOne(fetch = FetchType.LAZY)
    private PropertyJpaEntity property;

    @Embedded
    private ExchangeSnippetJpaEntity snippet;

    @Enumerated(EnumType.STRING)
    private ExchangeStatus status;

}
