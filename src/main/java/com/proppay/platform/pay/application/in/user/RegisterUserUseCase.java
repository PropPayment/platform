package com.proppay.platform.pay.application.in.user;

import com.proppay.platform.pay.adapter.in.web.dto.UserRequest;
import com.proppay.platform.pay.domain.user.User;

public interface RegisterUserUseCase {
    /*
        유저로 회원가입 요청
     */

    User registerUser(UserRequest request);

}
