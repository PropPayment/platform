package com.proppay.platform.pay.adapter.in.web.dto;

import com.proppay.platform.pay.domain.admin.AdminRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class AdminRequest {

    @NotBlank(message = "관리자 아이디는 필수입니다.")
    @Size(min = 4, max = 20, message = "관리자 아이디는 4자 이상 20자 이하여야 합니다.") // 예시로 길이 제한 추가
    private String userId;  // 관리자 아이디

    @NotBlank(message = "비밀번호는 필수입니다.")
    @Size(min = 8, message = "비밀번호는 최소 8자 이상이어야 합니다.") // 비밀번호 최소 길이 제한
    private String password; // 관리자 비밀번호

    private AdminRole role;  // 관리자의 역할 (최상위 관리자, 매물 관리자 등)

}
