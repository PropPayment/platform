package com.proppay.platform.pay.application.in.exchange;

public interface RejectExchangeUseCase {

    /*
        1:1 거래 이후, 요청이 들어올 시 거절하는 유즈케이스
        교환 요청 엔티티가 삭제된다.
     */

    void rejectExchange(Long exchangeId);



}
