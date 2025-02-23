package com.proppay.platform.pay.application.in.lawyer;

import com.proppay.platform.pay.adapter.in.web.dto.LawyerConditionRequest;
import com.proppay.platform.pay.domain.lawyer.Lawyer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ListLawyersUseCase {

    /*
        법무사 목록 가져오기 (최신순 정렬) ,
        지역별
        인기순 정렬

        추후, 법무사에 리뷰를 다는 형식 제공
     */

    // 승인된 법무사 목록 확인
    Page<Lawyer> getList(Pageable pageable);

    // 미승인된 법무사 목록 확인
    Page<Lawyer> getListNotApproved(Pageable pageable);

    // 탈퇴된 법무사 목록 확인
    Page<Lawyer> getListRejected(Pageable pageable);

    Page<Lawyer> getListByRegion(Pageable pageable, LawyerConditionRequest request);

    Page<Lawyer> getListByLikeCount(Pageable pageable);
}
