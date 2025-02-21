package com.proppay.platform.pay.application.in.property;

import com.proppay.platform.pay.application.in.dto.PropertyRequest;
import com.proppay.platform.pay.domain.property.Property;

public interface CreatePropertyUseCase {

    /*
        교환할 매물정보 등록하기
     */

    Property create(PropertyRequest request); // 매물 생성



}
