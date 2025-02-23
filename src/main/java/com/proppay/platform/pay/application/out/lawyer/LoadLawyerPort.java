package com.proppay.platform.pay.application.out.lawyer;

import com.proppay.platform.pay.domain.lawyer.Lawyer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface LoadLawyerPort {

    Optional<Lawyer> loadLawyer(Long id);

    boolean existsLawyer(Long id);

    // 승인된 법무사 전체 조회, 최신순
    Page<Lawyer> loadLawyers(Pageable pageable);

    // 승인된 조회, 인기순

    // 승인된 지역별, 최신순
    Page<Lawyer> loadLawyersByRegion(String city, String district, String dong, Pageable pageable);

    // 승인된 지역별, 인기순
    Page<Lawyer> loadLawyersByRegionAndLike(String city, String district, String dong, Pageable pageable);

    // 미승인된 법무사 전체 조회
    Page<Lawyer> loadLawyersNotApproved(Pageable pageable);

    // 탈퇴된 법무사 전체 조회
    Page<Lawyer> loadLawyersRejected(Pageable pageable);


}
