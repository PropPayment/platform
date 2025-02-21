package com.proppay.platform.pay.application.in.broker;

import com.proppay.platform.pay.domain.broker.Broker;

import java.util.Optional;

public interface GetBrokerUseCase {

    /*
        공인중개사 상세 정보를 확인하는 유즈 케이스
     */

    Optional<Broker> getBroker(Long brokerId);

}
