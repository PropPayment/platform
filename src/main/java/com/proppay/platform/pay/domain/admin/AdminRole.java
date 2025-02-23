package com.proppay.platform.pay.domain.admin;

public enum AdminRole {
    SUPER_ADMIN,      // 최상위 관리자 (모든 권한 보유)
    BROKER_MANAGER,   // 공인중개사 승인/관리 담당
    LAWYER_MANAGER,   // 공인중개사 승인/관리 담당
    PROPERTY_MANAGER, // 매물 등록 및 관리 담당
    CONTENT_MANAGER,  // 게시글 및 리뷰 관리 담당
    CUSTOMER_SUPPORT  // 고객 지원 담당 (신고 처리, 문의 응대)
}
