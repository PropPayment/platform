package com.proppay.platform.pay.application.in.broker;

import com.proppay.platform.pay.adapter.in.web.dto.BrokerRequest;
import com.proppay.platform.pay.domain.broker.Broker;

public interface RegisterBrokerUseCase {

    /*
        공인중개사로 회원가입
     */

    Broker registerBroker(BrokerRequest request);

}
