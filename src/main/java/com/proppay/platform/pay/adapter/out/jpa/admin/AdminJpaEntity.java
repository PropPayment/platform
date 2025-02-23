package com.proppay.platform.pay.adapter.out.jpa.admin;

import com.proppay.platform.pay.domain.admin.Admin;
import com.proppay.platform.pay.domain.admin.AdminRole;
import com.proppay.platform.pay.domain.admin.AdminStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdminJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String userId;  // 관리자 ID

    @Column(nullable = false)
    private String password;  // 비밀번호 (암호화 저장 필요)

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AdminRole role;  // 관리자 역할 (최상위 관리자, 매물 관리자 등)

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AdminStatus status;  // 관리자 상태 (ACTIVE, INACTIVE 등)

    // from
    public static AdminJpaEntity from(Admin admin) {
        return AdminJpaEntity.builder()
                .userId(admin.getUserId())
                .password(admin.getPassword())
                .role(admin.getRole())
                .status(admin.getStatus())
                .build();
    }

    // toDomain
    public Admin toDomain() {
        return Admin.builder()
                .id(this.id)
                .userId(this.userId)
                .password(this.password)
                .role(this.role)
                .status(this.status)
                .build();
    }
}
