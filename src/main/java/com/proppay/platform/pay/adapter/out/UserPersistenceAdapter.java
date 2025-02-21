package com.proppay.platform.pay.adapter.out;

import com.proppay.platform.pay.adapter.out.jpa.user.UserJpaEntity;
import com.proppay.platform.pay.adapter.out.jpa.user.UserJpaEntityRepository;
import com.proppay.platform.pay.application.out.user.LoadUserPort;
import com.proppay.platform.pay.application.out.user.SaveUserPort;
import com.proppay.platform.pay.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserPersistenceAdapter implements SaveUserPort, LoadUserPort {

    private final UserJpaEntityRepository repository;

    @Override
    public User saveUser(User user) {
        var entity = UserJpaEntity.from(user);
        return repository.save(entity).toDomain();
    }

    @Override
    public Optional<User> loadUser(Long id) {
        return repository.findById(id)
                .map(UserJpaEntity::toDomain);
    }

    @Override
    public long loadAllUsers() {
        return repository.count();
    }


}
