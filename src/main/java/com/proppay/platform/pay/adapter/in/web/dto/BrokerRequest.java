package com.proppay.platform.pay.adapter.in.web.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BrokerRequest {

    @NotBlank(message = "이름은 필수입니다.")
    private String name;

    @NotBlank(message = "등록번호는 필수입니다.")
    private String licenseNumber;

    @NotBlank(message = "연락처는 필수입니다.")
    @Pattern(regexp = "\\d{10,11}", message = "올바른 전화번호 형식을 입력하세요.")
    private String phoneNumber;

    @NotBlank(message = "도로명 주소는 필수입니다.")
    private String streetAddress;

    @NotBlank(message = "상세 주소는 필수입니다.")
    private String detailAddress;

    @NotBlank(message = "우편번호는 필수입니다.")
    private String zipCode;
}
