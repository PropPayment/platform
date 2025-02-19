package com.proppay.platform.pay.domain.broker;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor

public class Broker {

    private Long id;

    private String name;  // 공인중개사 이름
    private String licenseNumber;  // 공인중개사 등록번호
    private String phoneNumber;  // 연락처
    private String officeAddress;  // 중개사무소 주소

    @Enumerated(EnumType.STRING)
    private BrokerStatus status;  // 승인 여부 (PENDING, APPROVED, REJECTED)
}
