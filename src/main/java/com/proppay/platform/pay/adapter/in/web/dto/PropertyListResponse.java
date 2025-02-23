package com.proppay.platform.pay.adapter.in.web.dto;

import com.proppay.platform.core.util.AddressParser;
import com.proppay.platform.pay.domain.property.Property;
import com.proppay.platform.pay.domain.property.PropertyAddress;
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
    private String dong;
    private long viewCount;
    private long likeCount;

    public static PropertyListResponse from(Property property) {

        PropertyAddress address = AddressParser.parseAddress(property.getAddress().getStreetAddress());


        return PropertyListResponse.builder()
                .id(property.getId())
                .title(property.getSnippet().getAptName())
                .type(property.getType())
                .price(property.getPrice())
                .city(address.getCity())
                .district(address.getDistrict())
                .dong(address.getDong())
                .viewCount(property.getStatistic().getViewCount())
                .likeCount(property.getStatistic().getLikeCount())
                .build();
    }

    public static List<PropertyListResponse> from(List<Property> properties) {
        return properties.stream().map(PropertyListResponse::from).toList();
    }

}
