package com.proppay.platform.pay.adapter.out.jpa.lawyer;

import com.proppay.platform.pay.domain.lawyer.LawyerStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LawyerJpaEntityRepository extends JpaRepository<LawyerJpaEntity, Long> {

    @Query("SELECT l FROM LawyerJpaEntity l WHERE l.status = :status ORDER BY l.createdAt DESC")
    Page<LawyerJpaEntity> findAllByOrderByCreatedAtDesc(@Param("status") LawyerStatus status, Pageable pageable);

    @Query("SELECT l FROM LawyerJpaEntity l WHERE l.status = :status ORDER BY l.statistics.likeCount DESC")
    Page<LawyerJpaEntity> findAllOrderByLikeCount(@Param("status") LawyerStatus status, Pageable pageable);

    @Query("SELECT l FROM LawyerJpaEntity l WHERE l.status = :status AND l.address.city = :city AND l.address.district = :district AND l.address.dong = :dong")
    Page<LawyerJpaEntity> findByRegion(@Param("status") LawyerStatus status,@Param("city") String city, @Param("district") String district, @Param("dong") String dong, Pageable pageable);

    @Query("SELECT l FROM LawyerJpaEntity l WHERE l.status = :status AND l.address.city = :city AND l.address.district = :district AND l.address.dong = :dong order by l.statistics.likeCount DESC ")
    Page<LawyerJpaEntity> findByRegionAndLikeCount(@Param("status") LawyerStatus status, @Param("city") String city, @Param("district") String district, @Param("dong") String dong, Pageable pageable);


}
