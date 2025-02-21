package com.proppay.platform.pay.adapter.in.web.dto;

import com.proppay.platform.pay.domain.property.Property;
import com.proppay.platform.pay.domain.property.PropertyType;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PropertyDetailResponse {

    private Long id;
    private String title;
    private String description;
    private PropertyType type;
    private boolean verified;

    private String streetAddress;
    private String detailAddress;
    private String city;
    private String district;
    private String postalCode;

    private long price;
    private long viewCount;
    private long likeCount;

    private Long ownerId;
    private String ownerUsername;

    private int quantity;
    private int bathrooms;
    private int builtYear;

    public static PropertyDetailResponse from(Property property) {
        return PropertyDetailResponse.builder()
                .id(property.getId())
                .title(property.getSnippet().getTitle())
                .description(property.getSnippet().getDescription())
                .type(property.getType())
                .verified(property.isVerified())
                .streetAddress(property.getAddress().getStreetAddress())
                .detailAddress(property.getAddress().getDetailAddress())
                .city(property.getAddress().getCity())
                .district(property.getAddress().getDistrict())
                .postalCode(property.getAddress().getPostalCode())
                .price(property.getPrice())
                .viewCount(property.getStatistic().getViewCount())
                .likeCount(property.getStatistic().getLikeCount())
                .ownerId(property.getOwner().getId())
                .ownerUsername(property.getOwner().getUsername())
                .quantity(property.getSnippet().getQuantity())
                .bathrooms(property.getSnippet().getBathrooms())
                .builtYear(property.getSnippet().getBuiltYear())
                .build();
    }
}
