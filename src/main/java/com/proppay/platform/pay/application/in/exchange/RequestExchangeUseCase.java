package com.proppay.platform.pay.application.in.exchange;

import com.proppay.platform.pay.adapter.in.web.dto.ExchangeRequest;
import com.proppay.platform.pay.domain.exchange.Exchange;

public interface RequestExchangeUseCase {

    /*
        1:1 대화 이후, 조건에 부합하다고 생각할 시 거래 요청
     */

    Exchange requestExchange(ExchangeRequest request);


}
