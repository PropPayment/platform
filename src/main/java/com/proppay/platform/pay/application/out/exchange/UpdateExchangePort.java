package com.proppay.platform.pay.application.out.exchange;

import com.proppay.platform.pay.domain.exchange.Exchange;
import com.proppay.platform.pay.domain.exchange.ExchangeStatus;

import java.util.Optional;

public interface UpdateExchangePort {

    Optional<Exchange> updateExchangeStatus(Long exchangeId, ExchangeStatus status);

}
