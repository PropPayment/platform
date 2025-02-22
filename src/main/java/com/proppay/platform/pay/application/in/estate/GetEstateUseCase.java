package com.proppay.platform.pay.application.in.estate;

import com.proppay.platform.pay.domain.estate.Estate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface GetEstateUseCase {

    /*
        아파트 정보 가져오는 유즈케이스
     */

    // 특정 좌표 주변의 아파트 가져오기
    List<Estate> loadEstatesNearBy(double latitude, double longitude, double radiusInKm);

    // 코드를 바탕으로 아파트 가져오기
    Optional<Estate> loadEstateByCode(String kaptCode);

    // 이름을 바탕으로 아파트 가져오기
    Optional<Estate> loadEstateByName(String kaptName);

    // 특정 지역(동)을 포함하는 아파트 목록 페이징 조회
    Page<Estate> loadEstatesByRegion(String region, Pageable pageable);



}
