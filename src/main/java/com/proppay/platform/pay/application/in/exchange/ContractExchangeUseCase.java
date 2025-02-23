package com.proppay.platform.pay.application.in.exchange;

import com.proppay.platform.pay.adapter.in.web.dto.ExchangeDetailResponse;

public interface ContractExchangeUseCase {

    /*
        매칭 이후의 상태변화를 관리하는 유즈케아스
        거래 요청 -> 거절 : 다시 생성해야 함
        거래 요청 -> 승낙 : 가능 (법무사 지정 필수)
        승낙 -> 취소 : 가능
        체결 -> 취소 : 불가능
     */

    // 승인, 공인중개사도 새롭게 추가해야함
    ExchangeDetailResponse approveExchange(Long exchangeId, Long lawyerId);

    // 취소
    ExchangeDetailResponse cancelExchange(Long exchangeId);

    // 체결
    ExchangeDetailResponse completeExchange(Long exchangeId);

}
