package com.proppay.platform.pay.application.in.lawyer;

import com.proppay.platform.pay.domain.lawyer.Lawyer;

import java.util.Optional;

public interface GetLawyerUseCase {

    /*
        법무사 상세 정보를 확인하는 유즈 케이스
     */

    Optional<Lawyer> getLawyer(Long lawyerId);

}
