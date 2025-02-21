package com.proppay.platform.pay.application.out.broker;

import com.proppay.platform.pay.domain.broker.Broker;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface LoadBrokerPort {

    Optional<Broker> loadBroker(Long id);

    boolean existsBroker(Long id);

    // 승인된 공인중개사 전체 조회, 최신순
    Page<Broker> loadBrokers(Pageable pageable);

    // 승인된 조회, 인기순

    // 승인된 지역별, 최신순
    Page<Broker> loadBrokersByRegion(String city, String district, String dong, Pageable pageable);

    // 승인된 지역별, 인기순
    Page<Broker> loadBrokersByRegionAndLike(String city, String district, String dong, Pageable pageable);

    // 미승인된 공인중개사 전체 조회
    Page<Broker> loadBrokersNotApproved(Pageable pageable);

    // 탈퇴된 공인중개사 전체 조회
    Page<Broker> loadBrokersRejected(Pageable pageable);


}
