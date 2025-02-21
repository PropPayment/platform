package com.proppay.platform.pay.application.in.property;

import com.proppay.platform.pay.domain.property.Property;

import java.util.Optional;

public interface GetPropertyDetailsUseCase {

    /*
        매물 상세정보 가져오기
     */

    Optional<Property> getPropertyDetails(Long propertyId);


}
