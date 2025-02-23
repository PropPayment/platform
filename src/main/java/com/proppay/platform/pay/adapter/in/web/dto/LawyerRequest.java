package com.proppay.platform.pay.adapter.in.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LawyerRequest {

    private String name; // 법무사 이름
    private String licenseNumber; // 법무사 자격 번호
    private String contact; // 연락처 (전화번호, 이메일 등)

    private String streetAddress;
    private String detailAddress;
    private String postalCode;

    private String region;

    private String workingHours; // 근무 시간 (예: "09:00-18:00")
    private String affiliatedAgency; // 소속 기관
    private boolean remoteSupportAvailable; // 비대면 계약 지원 가능 여부

}

