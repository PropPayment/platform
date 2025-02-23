package com.proppay.platform.pay.adapter.in.web.dto;

import com.proppay.platform.pay.domain.property.Property;
import com.proppay.platform.pay.domain.property.PropertyType;
import lombok.Builder;
import lombok.Getter;

import java.util.*;

@Getter
@Builder
public class PropertyListResponse {
    private Long id;
    private String title;
    private PropertyType type;
    private long price;
    private String city;
    private String district;
    private long viewCount;
    private long likeCount;

    public static PropertyListResponse from(Property property) {
        return PropertyListResponse.builder()
                .id(property.getId())
                .title(property.getSnippet().getAptName())
                .type(property.getType())
                .price(property.getPrice())
                .city(property.getAddress().getCity())
                .district(property.getAddress().getDistrict())
                .viewCount(property.getStatistic().getViewCount())
                .likeCount(property.getStatistic().getLikeCount())
                .build();
    }

    public static List<PropertyListResponse> from(List<Property> properties) {
        return properties.stream().map(PropertyListResponse::from).toList();
    }

}
