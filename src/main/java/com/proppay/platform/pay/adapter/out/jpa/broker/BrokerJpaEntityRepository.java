package com.proppay.platform.pay.adapter.out.jpa.broker;

import com.proppay.platform.pay.adapter.out.jpa.property.PropertyJpaEntity;
import com.proppay.platform.pay.domain.broker.BrokerStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BrokerJpaEntityRepository extends JpaRepository<BrokerJpaEntity, Long> {

    @Query("SELECT b FROM BrokerJpaEntity b WHERE b.status = :status ORDER BY b.createdAt DESC")
    Page<BrokerJpaEntity> findAllByOrderByCreatedAtDesc(@Param("status") BrokerStatus status, Pageable pageable);

    @Query("SELECT b FROM BrokerJpaEntity b WHERE b.status = :status ORDER BY b.statistics.likeCount DESC")
    Page<BrokerJpaEntity> findAllOrderByLikeCount(@Param("status") BrokerStatus status, Pageable pageable);

    @Query("SELECT b FROM BrokerJpaEntity b WHERE b.status = :status AND b.address.city = :city AND b.address.district = :district AND b.address.dong = :dong")
    Page<BrokerJpaEntity> findByRegion(@Param("status") BrokerStatus status,@Param("city") String city, @Param("district") String district, @Param("dong") String dong, Pageable pageable);

    @Query("SELECT b FROM BrokerJpaEntity b WHERE b.status = :status AND b.address.city = :city AND b.address.district = :district AND b.address.dong = :dong order by b.statistics.likeCount DESC ")
    Page<BrokerJpaEntity> findByRegionAndLikeCount(@Param("status") BrokerStatus status, @Param("city") String city, @Param("district") String district, @Param("dong") String dong, Pageable pageable);


}
