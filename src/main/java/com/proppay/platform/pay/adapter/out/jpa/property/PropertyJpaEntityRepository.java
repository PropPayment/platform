package com.proppay.platform.pay.adapter.out.jpa.property;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PropertyJpaEntityRepository extends JpaRepository<PropertyJpaEntity, Long> {

    @Query("SELECT p FROM PropertyJpaEntity p ORDER BY p.createdAt DESC")
    Page<PropertyJpaEntity> findAllByOrderByCreatedAtDesc(Pageable pageable);

    @Query("SELECT p FROM PropertyJpaEntity p ORDER BY p.statistic.likeCount DESC")
    Page<PropertyJpaEntity> findAllOrderByLikeCount(Pageable pageable);

    @Query("SELECT p FROM PropertyJpaEntity p ORDER BY p.statistic.viewCount DESC")
    Page<PropertyJpaEntity> findAllOrderByViewCount(Pageable pageable);

}
