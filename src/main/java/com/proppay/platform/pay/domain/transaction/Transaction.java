package com.proppay.platform.pay.domain.transaction;

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
public class Transaction {

    private Long id;

    private User seller;
    private User buyer;
    private Property property;

    private TransactionSnippet snippet;
    private TransactionStatus status;

    // 생성자
    public static Transaction of(User seller, User buyer, Property property,
                                 TransactionSnippet snippet) {
        return Transaction.builder()
                .seller(seller)
                .buyer(buyer)
                .property(property)
                .snippet(snippet)
                .status(TransactionStatus.REQUESTED) // 기본값: 대기 중
                .build();
    }

}
