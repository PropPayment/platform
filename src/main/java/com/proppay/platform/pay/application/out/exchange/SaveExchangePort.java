package com.proppay.platform.pay.application.out.exchange;

import com.proppay.platform.pay.domain.exchange.Exchange;

public interface SaveExchangePort {

    Exchange saveExchange(Exchange exchange);
}
