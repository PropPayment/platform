package com.proppay.platform.pay.application.out.property;

import com.proppay.platform.pay.adapter.in.web.dto.PropertyConditionRequest;
import com.proppay.platform.pay.domain.property.Property;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface LoadPropertyPort {

    Optional<Property> loadProperty(Long id);

    // 기본 최신순
    Page<Property> loadList(Pageable pageable);

    // 인기순 가져오기
    Page<Property> loadListByLikeCount(Pageable pageable);

    // 조회순 가져오기
    Page<Property> loadListByViewCount(Pageable pageable);

    // 조건에 따른 정보 가져오기
    Page<Property> loadListByCondition(Pageable pageable, PropertyConditionRequest request);


}
