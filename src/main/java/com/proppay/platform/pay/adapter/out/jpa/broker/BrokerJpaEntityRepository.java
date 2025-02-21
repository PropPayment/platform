package com.proppay.platform.pay.adapter.out.jpa.broker;

import com.proppay.platform.pay.adapter.out.jpa.property.PropertyJpaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BrokerJpaEntityRepository extends JpaRepository<BrokerJpaEntity, Long> {

    @Query("SELECT b FROM BrokerJpaEntity b ORDER BY b.createdAt DESC")
    Page<PropertyJpaEntity> findAllByOrderByCreatedAtDesc(Pageable pageable);

    @Query("SELECT b FROM BrokerJpaEntity b ORDER BY b.statistics.likeCount DESC")
    Page<PropertyJpaEntity> findAllOrderByLikeCount(Pageable pageable);

}
