package com.proppay.platform.pay.adapter.out.jpa.broker;

import com.proppay.platform.pay.adapter.out.jpa.BaseEntity;
import com.proppay.platform.pay.domain.broker.Broker;
import com.proppay.platform.pay.domain.broker.BrokerStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BrokerJpaEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;  // 공인중개사 이름

    @Column(unique = true)
    private String licenseNumber;  // 공인중개사 등록번호

    private String phoneNumber;  // 연락처

    @Embedded
    private BrokerAddressJpaEntity address;  // 중개사무소 주소

    @Embedded
    private BrokerStatisticsJpaEntity statistics;

    @Enumerated(EnumType.STRING)
    private BrokerStatus status;  // 승인 여부 (PENDING, APPROVED, REJECTED)

    public static BrokerJpaEntity from(Broker broker) {
        return BrokerJpaEntity.builder()
                .id(broker.getId())
                .name(broker.getName())
                .licenseNumber(broker.getLicenseNumber())
                .phoneNumber(broker.getPhoneNumber())
                .address(BrokerAddressJpaEntity.from(broker.getAddress()))
                .statistics(BrokerStatisticsJpaEntity.from(broker.getStatistics()))
                .status(broker.getStatus())
                .build();
    }

    public Broker toDomain() {
        return Broker.builder()
                .id(this.id)
                .name(this.name)
                .licenseNumber(this.licenseNumber)
                .phoneNumber(this.phoneNumber)
                .address(this.address.toDomain())
                .statistics(this.statistics.toDomain())
                .status(this.status)
                .build();
    }

}
