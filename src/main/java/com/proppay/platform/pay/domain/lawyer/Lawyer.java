package com.proppay.platform.pay.domain.lawyer;

import com.proppay.platform.pay.domain.admin.Admin;
import com.proppay.platform.pay.domain.admin.AdminRole;
import com.proppay.platform.pay.domain.admin.AdminStatus;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Slf4j
public class Lawyer {

    private Long id; // 기본 키

    private String name; // 법무사 이름

    private String licenseNumber; // 법무사 자격 번호

    private String contact; // 연락처 (전화번호, 이메일 등)

    private LawyerAddress address; // 사무소 주소

    private LawyerSnippet snippet;

    private LawyerStatistics statistics;

    private LawyerStatus status;

    // 생성자
    public static Lawyer of(String name, String licenseNumber, String contact, LawyerAddress address, LawyerSnippet snippet) {

        LawyerStatistics statistics = LawyerStatistics.of(0.0, 0, 0);

        return Lawyer.builder()
                .name(name)
                .licenseNumber(licenseNumber)
                .contact(contact)
                .address(address)
                .snippet(snippet)
                .statistics(statistics)
                .status(LawyerStatus.PENDING)
                .build();
    }

    // 승인하기
    public void approve(Admin admin) {
        if (this.status == LawyerStatus.APPROVED) {
            throw new IllegalStateException("이미 승인된 법무사입니다.");
        }

        if (admin.getStatus() != AdminStatus.ACTIVE) {
            throw new IllegalStateException("비활성화된 관리자는 승인할 수 없습니다.");
        }

        if (admin.getRole() != AdminRole.BROKER_MANAGER && admin.getRole() != AdminRole.SUPER_ADMIN) {
            throw new IllegalStateException("승인 권한이 없는 관리자입니다.");
        }

        this.status = LawyerStatus.APPROVED;
        log.info("법무사 : {},승인한 관리자 : {}", this.getLicenseNumber(), admin.getId());
    }

}
