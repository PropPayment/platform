package com.proppay.platform.pay.application.in.property;


public interface DeletePropertyUseCase {

    /*
        교환할 매물정보 삭제하기
     */

    void deleteProperty(Long id);

}
