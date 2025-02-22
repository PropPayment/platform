package com.proppay.platform.pay.adapter.out;

import com.proppay.platform.pay.adapter.out.mongo.estate.EstateDocument;
import com.proppay.platform.pay.adapter.out.mongo.estate.EstateMongoRepository;
import com.proppay.platform.pay.application.out.estate.LoadEstatePort;
import com.proppay.platform.pay.domain.estate.Estate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class EstatePersistenceAdapter implements LoadEstatePort {

    private final EstateMongoRepository repository;

    @Override
    public Optional<Estate> loadEstateByCode(String kaptCode) {
        return repository.findByKaptCode(kaptCode)
                .map(EstateDocument::toDomain);
    }

    @Override
    public Optional<Estate> loadEstateByName(String kaptName) {
        return repository.findByKaptName(kaptName)
                .map(EstateDocument::toDomain);
    }

    @Override
    public Page<Estate> loadEstatesByRegion(String region, Pageable pageable) {
        return repository.findByKaptAddrContaining(region, pageable)
                .map(EstateDocument::toDomain);
    }

}
