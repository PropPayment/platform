package com.proppay.platform.pay.adapter.out;

import com.proppay.platform.pay.adapter.out.jpa.admin.AdminJpaEntity;
import com.proppay.platform.pay.adapter.out.jpa.admin.AdminJpaEntityRepository;
import com.proppay.platform.pay.application.out.admin.AdminPort;
import com.proppay.platform.pay.domain.admin.Admin;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class AdminPersistenceAdapter implements AdminPort {

    private final AdminJpaEntityRepository repository;

    @Override
    public Admin SaveAdmin(Admin admin) {
        var entity = AdminJpaEntity.from(admin);
        return repository.save(entity).toDomain();
    }

    @Override
    public Optional<Admin> loadAdmin(Long id) {
        return repository.findById(id)
                .map(AdminJpaEntity::toDomain);
    }

    @Override
    public void deleteAdmin(Long id) {
        repository.deleteById(id);
    }
}
