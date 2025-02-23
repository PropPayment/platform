package com.proppay.platform.pay.adapter.out.jpa.lawyer;

import com.proppay.platform.pay.adapter.out.jpa.BaseEntity;
import com.proppay.platform.pay.domain.lawyer.Lawyer;
import com.proppay.platform.pay.domain.lawyer.LawyerStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LawyerJpaEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 기본 키

    private String name; // 법무사 이름

    @Column(unique = true, nullable = false)
    private String licenseNumber; // 법무사 자격 번호

    private String contact; // 연락처 (전화번호, 이메일 등)

    @Embedded
    private LawyerAddressJpaEntity address; // 사무소 주소

    @Embedded
    private LawyerSnippetJpaEntity snippet;

    @Embedded
    private LawyerStatisticsJpaEntity statistics;

    @Enumerated(EnumType.STRING)
    private LawyerStatus status;  // 승인 여부 (PENDING, APPROVED, REJECTED)

    // from
    public static LawyerJpaEntity from(Lawyer lawyer) {
        return LawyerJpaEntity.builder()
                .name(lawyer.getName())
                .licenseNumber(lawyer.getLicenseNumber())
                .contact(lawyer.getContact())
                .address(LawyerAddressJpaEntity.from(lawyer.getAddress()))
                .snippet(LawyerSnippetJpaEntity.from(lawyer.getSnippet()))
                .statistics(LawyerStatisticsJpaEntity.from(lawyer.getStatistics()))
                .status(lawyer.getStatus())
                .build();
    }

    // toDomain
    public Lawyer toDomain() {
        return Lawyer.builder()
                .id(id)
                .name(name)
                .licenseNumber(licenseNumber)
                .contact(contact)
                .address(address.toDomain())
                .snippet(snippet.toDomain())
                .statistics(statistics.toDomain())
                .status(status)
                .build();
    }
}

