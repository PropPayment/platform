package com.proppay.platform.pay.domain.broker;

import com.proppay.platform.pay.domain.admin.Admin;
import com.proppay.platform.pay.domain.admin.AdminRole;
import com.proppay.platform.pay.domain.admin.AdminStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class Broker {

    private Long id;

    private String name;  // 공인중개사 이름
    private String licenseNumber;  // 공인중개사 등록번호
    private String phoneNumber;  // 연락처
    private BrokerAddress address;  // 중개사무소 주소
    private BrokerStatistics statistics; // 통계정보

    @Enumerated(EnumType.STRING)
    private BrokerStatus status;  // 승인 여부 (PENDING, APPROVED, REJECTED)

    // 생성자
    public static Broker of(String name, String licenseNumber, String phoneNumber, BrokerAddress address) {

        BrokerStatistics statistics = BrokerStatistics.of(0, 0.0, 0);

        return Broker.builder()
                .name(name)
                .licenseNumber(licenseNumber)
                .phoneNumber(phoneNumber)
                .address(address)
                .statistics(statistics)
                .status(BrokerStatus.PENDING)
                .build();
    }

    // 승인하기
    public void approve(Admin admin) {
        if (this.status == BrokerStatus.APPROVED) {
            throw new IllegalStateException("이미 승인된 공인중개사입니다.");
        }

        if (admin.getStatus() != AdminStatus.ACTIVE) {
            throw new IllegalStateException("비활성화된 관리자는 승인할 수 없습니다.");
        }

        if (admin.getRole() != AdminRole.BROKER_MANAGER && admin.getRole() != AdminRole.SUPER_ADMIN) {
            throw new IllegalStateException("승인 권한이 없는 관리자입니다.");
        }

        this.status = BrokerStatus.APPROVED;
        log.info("공인중개사 : {},승인한 관리자 : {}", this.getLicenseNumber(), admin.getId());
    }




}
