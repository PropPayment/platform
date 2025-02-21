package com.proppay.platform.pay.adapter.in.web.dto;

import com.proppay.platform.pay.domain.property.Property;
import com.proppay.platform.pay.domain.property.PropertyType;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PropertyDetailResponse {

    private Long id;
    private Long ownerId;
    private String ownerUsername;
    private PropertyType type;
    private long price;
    private boolean verified;

    private PropertySnippetResponse snippet;
    private PropertyStatisticsResponse statistics;

    public static PropertyDetailResponse from(Property property) {
        return PropertyDetailResponse.builder()
                .id(property.getId())
                .ownerId(property.getOwner().getId())
                .ownerUsername(property.getOwner().getUsername())
                .type(property.getType())
                .price(property.getPrice())
                .verified(property.isVerified())
                .snippet(PropertySnippetResponse.from(property.getSnippet(), property.getAddress()))
                .statistics(PropertyStatisticsResponse.from(property.getStatistic()))
                .build();
    }
}
