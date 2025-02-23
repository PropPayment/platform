package com.proppay.platform.pay.adapter.out.jpa.property;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.*;

public interface PropertyJpaEntityRepository extends JpaRepository<PropertyJpaEntity, Long> {

    @Query("SELECT p FROM PropertyJpaEntity p ORDER BY p.createdAt DESC")
    Page<PropertyJpaEntity> findAllByOrderByCreatedAtDesc(Pageable pageable);

    @Query("SELECT p FROM PropertyJpaEntity p ORDER BY p.statistic.likeCount DESC")
    Page<PropertyJpaEntity> findAllOrderByLikeCount(Pageable pageable);

    @Query("SELECT p FROM PropertyJpaEntity p ORDER BY p.statistic.viewCount DESC")
    Page<PropertyJpaEntity> findAllOrderByViewCount(Pageable pageable);

    boolean existsByOwnerId(Long ownerId);

    @Query("SELECT p FROM PropertyJpaEntity p WHERE p.ownerId = :ownerId AND p.id = :id")
    Optional<PropertyJpaEntity> findByOwnerIdAndId(@Param("ownerId") Long ownerId, @Param("id") Long id);

}
