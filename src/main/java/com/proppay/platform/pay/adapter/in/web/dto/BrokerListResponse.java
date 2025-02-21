package com.proppay.platform.pay.adapter.in.web.dto;
import com.proppay.platform.pay.domain.broker.Broker;
import lombok.Data;

import lombok.Builder;

import java.util.*;

@Data
@Builder
public class BrokerListResponse {

    private Long id;               // 공인중개사 ID
    private String name;           // 공인중개사 이름
    private String licenseNumber;  // 공인중개사 등록번호
    private String phoneNumber;    // 연락처
    private String city;           // 도시
    private String district;
    private Long likeCount;        // 좋아요 수
    private double reviewScore = 0.0;  // 평균 리뷰 점수

    public static BrokerListResponse from(Broker broker) {
        return BrokerListResponse.builder()
                .id(broker.getId())
                .name(broker.getName())
                .licenseNumber(broker.getLicenseNumber())
                .phoneNumber(broker.getPhoneNumber())
                .city(broker.getAddress().getCity())
                .district(broker.getAddress().getDistrict())
                .likeCount(broker.getStatistics().getLikeCount())
                .reviewScore(broker.getStatistics().getReviewScore())
                .build();
    }

    public static List<BrokerListResponse> from(List<Broker> brokers) {
        return brokers.stream()
                .map(BrokerListResponse::from).toList();
    }

}
