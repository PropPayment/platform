package com.proppay.platform.pay.application.in.lawyer;

public interface ApproveLawyerUseCase {

    /*
        관리자가 법무사를 확인하고
        법무사 역할로 업그레이드 해주는 유즈케이스
     */

    void approveLawyer(Long AdminId, Long lawyerId);

}
