package com.proppay.platform.pay.application.out.broker;

import com.proppay.platform.pay.domain.broker.Broker;

import java.util.Optional;

public interface LoadBrokerPort {

    Optional<Broker> loadBroker(Long id);

    boolean existsBroker(Long id);
}
