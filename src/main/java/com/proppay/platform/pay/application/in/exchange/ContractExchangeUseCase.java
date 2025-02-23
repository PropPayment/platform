package com.proppay.platform.pay.application.in.exchange;

import com.proppay.platform.pay.domain.exchange.Exchange;

public interface ContractExchangeUseCase {

    /*
        매칭 이후의 상태변화를 관리하는 유즈케아스
     */
    Exchange approveExchange(Long exchangeId);

    Exchange cancelExchange(Long exchangeId);

    Exchange completeExchange(Long exchangeId);

}
