package com.proppay.platform.pay.adapter.in.web.dto;

import com.proppay.platform.pay.domain.property.PropertyAddress;
import com.proppay.platform.pay.domain.property.PropertySnippet;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PropertySnippetResponse {
    private String title;
    private String description;

    private String streetAddress;
    private String detailAddress;
    private String city;
    private String district;
    private String postalCode;

    private int quantity;
    private int bathrooms;
    private int builtYear;

    public static PropertySnippetResponse from(PropertySnippet snippet, PropertyAddress address) {
        return PropertySnippetResponse.builder()
                .title(snippet.getTitle())
                .description(snippet.getDescription())
                .streetAddress(address.getStreetAddress())
                .detailAddress(address.getDetailAddress())
                .city(address.getCity())
                .district(address.getDistrict())
                .postalCode(address.getPostalCode())
                .quantity(snippet.getQuantity())
                .bathrooms(snippet.getBathrooms())
                .builtYear(snippet.getBuiltYear())
                .build();
    }
}
