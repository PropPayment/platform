package com.proppay.platform.pay.adapter.out;

import com.proppay.platform.pay.adapter.out.jpa.exchange.ExchangeJpaEntity;
import com.proppay.platform.pay.adapter.out.jpa.exchange.ExchangeJpaRepository;
import com.proppay.platform.pay.application.out.exchange.*;
import com.proppay.platform.pay.domain.exchange.Exchange;
import com.proppay.platform.pay.domain.exchange.ExchangeStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ExchangePersistenceAdapter implements SaveExchangePort, LoadExchangePort, DeleteExchangePort, UpdateExchangePort {

    private final ExchangeJpaRepository repository;

    @Override
    public Exchange saveExchange(Exchange exchange) {
        var entity = ExchangeJpaEntity.from(exchange);
        return repository.save(entity)
                .toDomain();
    }

    @Override
    public Optional<Exchange> updateExchangeStatus(Long exchangeId, ExchangeStatus status) {
        return repository.findById(exchangeId)
                .map(entity -> {
                    Exchange exchange = entity.toDomain();
                    exchange.changeStatus(status);
                    return repository.save(ExchangeJpaEntity.from(exchange)).toDomain();
                });
    }

    @Override
    public Optional<Exchange> loadExchange(Long id) {
        return repository.findById(id)
                .map(ExchangeJpaEntity::toDomain);
    }

    @Override
    public boolean existsExchange(Long exchangeId) {
        return repository.existsById(exchangeId);
    }


    @Override
    public void deleteExchange(Long exchangeId) {
        repository.deleteById(exchangeId);
    }

}
