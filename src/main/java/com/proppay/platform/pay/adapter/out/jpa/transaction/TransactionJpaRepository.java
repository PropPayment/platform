package com.proppay.platform.pay.adapter.out.jpa.transaction;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionJpaRepository extends JpaRepository<TransactionJpaEntity, Long> {
}
