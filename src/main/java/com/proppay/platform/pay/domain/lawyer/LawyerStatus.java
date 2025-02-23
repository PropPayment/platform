package com.proppay.platform.pay.domain.lawyer;

public enum LawyerStatus {
    PENDING,    // 대기
    APPROVED,   // 승인됨
    REJECTED,   // 거절됨
    REVOKED     // 권한 박탈됨 (DEPRIVED 대신 사용)
}
