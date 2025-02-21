package com.proppay.platform.pay.application.in.admin;

import com.proppay.platform.pay.adapter.in.web.dto.AdminRequest;
import com.proppay.platform.pay.domain.admin.Admin;

public interface CreateAdminUseCase {
    /*
        새로운 관리자 생성
        기존의 상위게층의 관리자가 생성 가능
        관리자가 아이디와 비밀번호 생성후 제공
     */

    Admin createAdmin(AdminRequest request); // 관리자 생성 메서드

    boolean isAuthorizedToCreateAdmin(Admin admin); // 상위 관리자만 생성할 수 있도록 권한 체크

}
