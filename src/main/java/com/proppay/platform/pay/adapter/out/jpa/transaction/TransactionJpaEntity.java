package com.proppay.platform.pay.adapter.out.jpa.transaction;

import com.proppay.platform.pay.adapter.out.jpa.property.PropertyJpaEntity;
import com.proppay.platform.pay.adapter.out.jpa.user.UserJpaEntity;
import com.proppay.platform.pay.domain.transaction.TransactionStatus;
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
public class TransactionJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private UserJpaEntity seller;

    @ManyToOne(fetch = FetchType.LAZY)
    private UserJpaEntity buyer;

    @OneToOne(fetch = FetchType.LAZY)
    private PropertyJpaEntity property;

    @Embedded
    private TransactionSnippetJpaEntity snippet;

    @Enumerated(EnumType.STRING)
    private TransactionStatus status;

}
