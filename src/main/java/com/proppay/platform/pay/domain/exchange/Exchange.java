package com.proppay.platform.pay.domain.exchange;

import com.proppay.platform.pay.domain.BaseDomain;
import com.proppay.platform.pay.domain.lawyer.Lawyer;
import com.proppay.platform.pay.domain.property.Property;
import com.proppay.platform.pay.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class Exchange extends BaseDomain {

    private Long id;

    private User seller;
    private User buyer;
    private Lawyer lawyer;

    private Property property;

    private ExchangeSnippet snippet;
    private ExchangeStatus status;

    // 생성자
    public static Exchange of(User seller, User buyer, Property property,Lawyer lawyer, ExchangeSnippet snippet) {

        checkUser(seller, buyer);

        return Exchange.builder()
                .seller(seller)
                .buyer(buyer)
                .property(property)
                .lawyer(lawyer)
                .snippet(snippet)
                .status(ExchangeStatus.REQUESTED) // 기본값: 대기 중
                .build();
    }

    // 동일 유저간 거래 금지
    private static void checkUser(User seller, User buyer) {
        if (seller.getId().equals(buyer.getId())) {
            throw new IllegalArgumentException("판매자와 구매자는 동일할 수 없습니다.");
        }
    }

    // 거래 대화 이후, 확정 시 법무사 연결
    public void addLawyer(Lawyer lawyer) {

        if (this.lawyer != null) {
            throw new IllegalStateException("이미 법무사가 배정되었습니다.");
        }
        this.lawyer = lawyer;
    }

    // 기존의 법무사를 새로운 법무사로 교체
    public void changeLawyer(Lawyer lawyer) {

        if (this.lawyer == null) {
            throw new IllegalStateException("교체할 법무사가 없습니다. 법무사를 배정해주세요.");
        }
        this.lawyer = lawyer;
    }

    // 교환 상태 수정하기
    public void changeStatus(ExchangeStatus status) {
        this.status = status;
    }

}
