package com.proppay.platform.pay.application.in.broker;

import com.proppay.platform.pay.adapter.in.web.dto.BrokerConditionRequest;
import com.proppay.platform.pay.domain.broker.Broker;
import com.proppay.platform.pay.domain.property.Property;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ListBrokersUseCase {

    /*
        공인중개사 목록 가져오기 (최신순 정렬) ,
        지역별
        인기순 정렬

        추후, 공인중개사에 리뷰를 다는 형식 제공
     */

    // 승인된 공인중개사 목록 확인
    Page<Broker> getList(Pageable pageable);

    // 미승인된 공인중개사 목록 확인
    Page<Broker> getListNotApproved(Pageable pageable);

    // 탈퇴된 공인중개사 목록 확인
    Page<Broker> getListRejected(Pageable pageable);

    Page<Property> getListByRegion(Pageable pageable, BrokerConditionRequest request);

    Page<Property> getListByLikeCount(Pageable pageable);
}
