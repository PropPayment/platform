package com.proppay.platform.pay.application.service;

import com.proppay.platform.pay.application.in.estate.GetEstateUseCase;
import com.proppay.platform.pay.application.out.estate.LoadEstatePort;
import com.proppay.platform.pay.domain.estate.Estate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EstateService implements GetEstateUseCase {

    private final LoadEstatePort loadPort;

    @Override
    public List<Estate> loadEstatesNearBy(double latitude, double longitude, double radiusInKm) {
        double radiusInRadians = radiusInKm / 6378.1;  // 반경 km -> radians 변환
        return loadPort.loadEstatesNearBy(latitude, longitude, radiusInRadians);
    }

    @Override
    public Optional<Estate> loadEstateByCode(String kaptCode) {
        return loadPort.loadEstateByCode(kaptCode);
    }

    @Override
    public Optional<Estate> loadEstateByName(String kaptName) {
        return loadPort.loadEstateByName(kaptName);
    }

    @Override
    public Page<Estate> loadEstatesByRegion(String region, Pageable pageable) {
        return loadPort.loadEstatesByRegion(region, pageable);
    }

}
