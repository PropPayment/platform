package com.proppay.platform.pay.application.out.estate;

import com.proppay.platform.pay.domain.estate.Estate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.*;
import java.util.Optional;

public interface LoadEstatePort {

    List<Estate> loadEstatesNearBy( double longitude, double latitude, double radiusInKm);

    Optional<Estate> loadEstateByCode(String kaptCode);

    Optional<Estate> loadEstateByName(String kaptName);

    Page<Estate> loadEstatesByRegion(String region, Pageable pageable);



}
