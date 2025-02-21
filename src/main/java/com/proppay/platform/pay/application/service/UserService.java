package com.proppay.platform.pay.application.service;

import com.proppay.platform.pay.adapter.in.web.dto.UserRequest;
import com.proppay.platform.pay.application.in.user.LoginUserUseCase;
import com.proppay.platform.pay.application.in.user.RegisterUserUseCase;
import com.proppay.platform.pay.application.in.user.UpdateUserProfileUseCase;
import com.proppay.platform.pay.application.out.user.SaveUserPort;
import com.proppay.platform.pay.domain.user.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService implements RegisterUserUseCase, LoginUserUseCase, UpdateUserProfileUseCase {

    private final SaveUserPort savePort;

    @Override
    public User registerUser(UserRequest request) {
        User user = User.of(request.getUsername(),
                request.getEmail(),
                request.getPassword(),
                request.getPhoneNumber());

        return savePort.saveUser(user);
    }

}
