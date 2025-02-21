package com.proppay.platform.pay.application.out;

import com.proppay.platform.pay.domain.user.User;

import java.util.Optional;

public interface LoadUserPort {

    // 유저 가져오기
    Optional<User> loadUser(Long id);

    // 유저 수 파악
    long loadAllUsers();


}
