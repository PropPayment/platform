package com.proppay.platform.pay.application.out.broker;

import com.proppay.platform.pay.domain.broker.Broker;

public interface SaveBrokerPort {

    Broker saveBroker(Broker broker);
}
