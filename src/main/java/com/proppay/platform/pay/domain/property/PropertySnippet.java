package com.proppay.platform.pay.domain.property;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class PropertySnippet {

    private String title;
    private String description;
    private int quantity; // 방 개수
    private int bathrooms; // 욕실 개수 추가
    private int builtYear; // 건축 연도 추가

    // 생성자
    public static PropertySnippet of(String title, String description, int quantity,
                                     int bathrooms, int builtYear) {
        return PropertySnippet.builder()
                .title(title)
                .description(description)
                .quantity(quantity)
                .bathrooms(bathrooms)
                .builtYear(builtYear)
                .build();
    }

}
