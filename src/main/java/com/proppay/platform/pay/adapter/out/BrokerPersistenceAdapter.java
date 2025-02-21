package com.proppay.platform.pay.adapter.out;

import com.proppay.platform.pay.adapter.out.jpa.broker.BrokerJpaEntity;
import com.proppay.platform.pay.adapter.out.jpa.broker.BrokerJpaEntityRepository;
import com.proppay.platform.pay.application.out.broker.DeleteBrokerPort;
import com.proppay.platform.pay.application.out.broker.LoadBrokerPort;
import com.proppay.platform.pay.application.out.broker.SaveBrokerPort;
import com.proppay.platform.pay.domain.broker.Broker;
import com.proppay.platform.pay.domain.broker.BrokerStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class BrokerPersistenceAdapter implements SaveBrokerPort, LoadBrokerPort, DeleteBrokerPort {

    private final BrokerJpaEntityRepository repository;

    @Override
    public Broker saveBroker(Broker broker) {
        var entity = BrokerJpaEntity.from(broker);
        return repository.save(entity).toDomain();
    }


    @Override
    public Optional<Broker> loadBroker(Long id) {
        return repository.findById(id)
                .map(BrokerJpaEntity::toDomain);
    }

    @Override
    public Page<Broker> loadBrokers(Pageable pageable) {
        Page<BrokerJpaEntity> result = repository.findAllByOrderByCreatedAtDesc(BrokerStatus.APPROVED,pageable);
        return convertToPage(result, pageable);
    }

    @Override
    public Page<Broker> loadBrokersNotApproved(Pageable pageable) {
        Page<BrokerJpaEntity> result = repository.findAllByOrderByCreatedAtDesc(BrokerStatus.PENDING, pageable);
        return convertToPage(result, pageable);
    }

    @Override
    public Page<Broker> loadBrokersRejected(Pageable pageable) {
        Page<BrokerJpaEntity> result = repository.findAllByOrderByCreatedAtDesc(BrokerStatus.REJECTED, pageable);
        return convertToPage(result, pageable);
    }

    @Override
    public Page<Broker> loadBrokersByRegion(String city, String district, String dong, Pageable pageable) {
        Page<BrokerJpaEntity> result = repository.findByRegion(BrokerStatus.APPROVED, city, district, dong, pageable);
        return convertToPage(result, pageable);
    }

    @Override
    public Page<Broker> loadBrokersByRegionAndLike(String city, String district, String dong, Pageable pageable) {
        Page<BrokerJpaEntity> result = repository.findByRegionAndLikeCount(BrokerStatus.APPROVED, city, district, dong, pageable);
        return convertToPage(result, pageable);

    }

    @Override
    public boolean existsBroker(Long id) {
        return repository.existsById(id);
    }


    @Override
    public void deleteBroker(Long id) {
        repository.deleteById(id);
    }


    /**
     * Page<BrokerJpaEntity>를 Page<Broker>로 변환하는 공통 메서드
     */

    private Page<Broker> convertToPage(Page<BrokerJpaEntity> result, Pageable pageable) {
        List<Broker> dtoList = result.map(BrokerJpaEntity::toDomain).getContent();
        return new PageImpl<>(dtoList, pageable, result.getTotalElements());
    }

}
