package com.proppay.platform.pay.adapter.in.web.dto;

import com.proppay.platform.pay.domain.broker.Broker;
import com.proppay.platform.pay.domain.broker.BrokerStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BrokerDetailResponse {

    private Long id;               // 공인중개사 ID
    private String name;           // 공인중개사 이름
    private String phoneNumber;    // 연락처
    private BrokerStatus status; // 상태
    private BrokerAddressResponse address;   // 중개사무소 주소 (상세 주소 포함)
    private BrokerStatisticsResponse statistics;  // 통계 정보

    public static BrokerDetailResponse from(Broker broker) {
        return BrokerDetailResponse.builder()
                .id(broker.getId())
                .name(broker.getName())
                .status(broker.getStatus())
                .address(BrokerAddressResponse.from(broker.getAddress()))
                .phoneNumber(broker.getPhoneNumber())
                .statistics(BrokerStatisticsResponse.from(broker.getStatistics()))
                .build();
    }
}
