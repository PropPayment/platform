package com.proppay.platform.pay.domain.lawyer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class LawyerSnippet {

    private String region; // 담당 지역

    private String workingHours; // 근무 시간 (예: "09:00-18:00")

    private String affiliatedAgency; // 소속 기관 (예: ○○ 법무법인)

    private boolean remoteSupportAvailable; // 비대면 계약 지원 가능 여부

    // 생성자
    public static LawyerSnippet of(String region, String workingHours, String affiliatedAgency, boolean remoteSupportAvailable) {
        return LawyerSnippet.builder()
                .region(region)
                .workingHours(workingHours)
                .affiliatedAgency(affiliatedAgency)
                .remoteSupportAvailable(remoteSupportAvailable)
                .build();
    }
}
