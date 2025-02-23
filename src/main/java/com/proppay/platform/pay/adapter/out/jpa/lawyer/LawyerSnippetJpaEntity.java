package com.proppay.platform.pay.adapter.out.jpa.lawyer;

import com.proppay.platform.pay.domain.lawyer.LawyerSnippet;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LawyerSnippetJpaEntity {

    private String region; // 담당 지역

    private String workingHours; // 근무 시간 (예: "09:00-18:00")

    private String affiliatedAgency; // 소속 기관 (예: ○○ 법무법인)

    private boolean remoteSupportAvailable; // 비대면 계약 지원 가능 여부

    // from
    public static LawyerSnippetJpaEntity from(LawyerSnippet snippet) {
        return LawyerSnippetJpaEntity.builder()
                .region(snippet.getRegion())
                .workingHours(snippet.getWorkingHours())
                .affiliatedAgency(snippet.getAffiliatedAgency())
                .remoteSupportAvailable(snippet.isRemoteSupportAvailable())
                .build();
    }

    // toDomain
    public LawyerSnippet toDomain() {
        return LawyerSnippet.builder()
                .region(region)
                .workingHours(workingHours)
                .affiliatedAgency(affiliatedAgency)
                .remoteSupportAvailable(remoteSupportAvailable)
                .build();
    }

}

