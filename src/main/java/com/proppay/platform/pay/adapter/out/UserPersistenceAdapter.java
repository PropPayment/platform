package com.proppay.platform.pay.adapter.out;

import com.proppay.platform.pay.adapter.out.jpa.user.UserJpaEntity;
import com.proppay.platform.pay.adapter.out.jpa.user.UserJpaEntityRepository;
import com.proppay.platform.pay.application.out.LoadUserPort;
import com.proppay.platform.pay.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserPersistenceAdapter implements LoadUserPort {

    private final UserJpaEntityRepository repository;

    @Override
    public Optional<User> loadUser(Long id) {

        return repository.findById(id)
                .map(UserJpaEntity::toDomain);
    }

}
