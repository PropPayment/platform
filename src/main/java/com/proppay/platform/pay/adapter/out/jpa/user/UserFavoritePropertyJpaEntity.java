package com.proppay.platform.pay.adapter.out.jpa.user;

import com.proppay.platform.pay.adapter.out.jpa.BaseEntity;
import com.proppay.platform.pay.adapter.out.jpa.property.PropertyJpaEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class UserFavoritePropertyJpaEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserJpaEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "property_id", nullable = false)
    private PropertyJpaEntity property;

    // from


    // toDomain

}
