package com.proppay.platform.pay.domain.exchange;

import com.proppay.platform.pay.domain.BaseDomain;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class Exchange extends BaseDomain {

    private Long id;

    private Long sellerId;
    private Long buyerId;

    private Long propertyId;
    private Long lawyerId;

    private ExchangeSnippet snippet;
    private ExchangeStatus status;

    // 생성자
    public static Exchange of(Long sellerId, Long buyerId, Long propertyId,ExchangeSnippet snippet) {

        checkUser(sellerId, buyerId);

        return Exchange.builder()
                .sellerId(sellerId)
                .buyerId(buyerId)
                .propertyId(propertyId)
                .snippet(snippet)
                .status(ExchangeStatus.REQUESTED) // 기본값: 대기 중
                .build();
    }

    // 동일 유저간 거래 금지
    private static void checkUser(Long sellerId, Long buyerId) {
        if (sellerId.equals(buyerId)) {
            throw new IllegalArgumentException("판매자와 구매자는 동일할 수 없습니다.");
        }
    }

    // 거래 대화 이후, 확정 시 법무사 연결
    public void addLawyer(Long lawyerId) {

        if (this.lawyerId != null) {
            throw new IllegalStateException("이미 법무사가 배정되었습니다.");
        }
        log.info("✅ {} 거래의 담당 법무사가 정해졌습니다.", this.id);

        this.lawyerId = lawyerId;
    }

    // 기존의 법무사를 새로운 법무사로 교체
    public void changeLawyer(Long lawyerId) {

        if (this.lawyerId == null) {
            throw new IllegalStateException("교체할 법무사가 없습니다. 법무사를 배정해주세요.");
        }
        log.info("✅ {} 거래의 담당 법무사가 변경되었습니다.", this.id);

        this.lawyerId = lawyerId;
    }

    // 교환 상태 수정하기
    public void changeStatus(ExchangeStatus status) {

        if (this.status .equals(ExchangeStatus.CONTRACTED)) {
            throw new IllegalStateException("계약완료된 상황에서는 취소할 수 없습니다.");
        }

        this.status = status;
    }

}
