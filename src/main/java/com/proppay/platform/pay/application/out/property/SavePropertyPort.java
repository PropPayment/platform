package com.proppay.platform.pay.application.out.property;

import com.proppay.platform.pay.domain.property.Property;

public interface SavePropertyPort {

    // 매물 등록하기
    Property saveProperty(Property property);

}
