package com.proppay.platform.pay.application.out.property;

public interface GetPropertyMetricsPort {

    long getPropertyLikeCount(Long propertyId);

    long getPropertyViewCount(Long propertyId);

}
