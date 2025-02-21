package com.proppay.platform.pay.domain.admin;

public enum AdminStatus {
    ACTIVE,      // 활성 상태 (정상적으로 활동 중)
    INACTIVE,    // 비활성 상태 (로그인 불가)
    SUSPENDED,   // 정지 상태 (일정 기간 동안 활동 제한)
    TERMINATED   // 계정 삭제 (완전한 제거)
}
