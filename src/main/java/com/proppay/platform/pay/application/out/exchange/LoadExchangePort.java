package com.proppay.platform.pay.application.out.exchange;

import com.proppay.platform.pay.domain.exchange.Exchange;

import java.util.Optional;

public interface LoadExchangePort {

    // 가져오기
    Optional<Exchange> loadExchange(Long id);

    // 거래 존재 여부
    boolean existsExchange(Long exchangeId);

}
