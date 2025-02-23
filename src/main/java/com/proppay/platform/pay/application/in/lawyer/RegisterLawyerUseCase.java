package com.proppay.platform.pay.application.in.lawyer;


import com.proppay.platform.pay.adapter.in.web.dto.LawyerRequest;
import com.proppay.platform.pay.domain.lawyer.Lawyer;

public interface RegisterLawyerUseCase {

    /*
        법무사로 회원가입
     */

    Lawyer registerBroker(LawyerRequest request);

}
