package com.proppay.platform.pay.adapter.out.jpa.property;

import com.proppay.platform.pay.domain.property.PropertySnippet;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Embeddable
public class PropertySnippetJpaEntity {

    private String title;
    private String description;
    private int quantity; // 방 개수
    private int bathrooms; // 욕실 개수 추가
    private int builtYear; // 건축 연도 추가


    // from
    public static PropertySnippetJpaEntity from(PropertySnippet snippet) {
        return PropertySnippetJpaEntity.builder()
                .title(snippet.getTitle())
                .description(snippet.getDescription())
                .quantity(snippet.getQuantity())
                .bathrooms(snippet.getBathrooms())
                .builtYear(snippet.getBuiltYear())
                .build();
    }

    // toDomain
    public PropertySnippet toDomain() {
        return PropertySnippet.builder()
                .title(title)
                .description(description)
                .quantity(quantity)
                .bathrooms(bathrooms)
                .builtYear(builtYear)
                .build();
    }
}
