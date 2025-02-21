package com.proppay.platform.pay.domain.admin;

import com.proppay.platform.pay.domain.BaseDomain;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class Admin extends BaseDomain {

    private Long id;

    private String userId;
    private String password;

    private AdminRole role;
    private AdminStatus status;

    private LocalDateTime lastLoginAt; //마지막 로그인 시간
    private int failedLoginAttempts = 0;  //로그인 실패 횟수 (보안 정책 적용 가능)
    private LocalDateTime passwordUpdatedAt; // 마지막 비밀번호 변경 시간 (비밀번호 변경 주기 관리)

    public Admin of (String userId, String password, AdminRole role, AdminStatus status, LocalDateTime lastLoginAt) {
        return Admin.builder()
                .userId(userId)
                .password(password)
                .role(role)
                .status(status)
                .lastLoginAt(lastLoginAt)
                .build();
    }

    // 로그인 실패
    public void failLoginAttempt() {

        // 실패할 때마다 로그인 횟수 추가 +
        this.failedLoginAttempts++;

        // 로그인 5회 이상 실패시, 변경을 요청해야함.
        if (failedLoginAttempts > 5) {
            this.status = AdminStatus.SUSPENDED;
        }
    }
}
