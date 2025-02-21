package com.proppay.platform.pay.application.out;

import com.proppay.platform.pay.domain.user.User;

public interface SaveUserPort {

    User saveUser(User user);
}
