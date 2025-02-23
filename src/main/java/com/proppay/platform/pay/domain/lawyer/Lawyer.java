package com.proppay.platform.pay.domain.lawyer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
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

}
