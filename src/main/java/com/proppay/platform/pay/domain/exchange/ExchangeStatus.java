package com.proppay.platform.pay.domain.exchange;

public enum ExchangeStatus {

    REQUESTED,  // 중개 요청됨 (매물 등록)
    MATCHED,    // 매수자와 매칭됨
    CONTRACTED, // 계약 완료됨
    CANCELED    // 중개 취소됨

}