package com.proppay.platform.pay.domain.estate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class Estate {

    private String id;
    private String kaptCode;
    private String pricePerSquareMeter;
    private String kaptAddr;
    private String kaptMparea_135;
    private String kaptMparea_136;
    private String kaptMparea_60;
    private String kaptMparea_85;
    private String kaptName;

    private Location location;
    private String convenientFacility;
    private String educationFacility;
    private String kaptdPcnt;
    private String kaptdPcntu;
    private String kaptdWtimebus;
    private String kaptdWtimesub;
    private String subwayLine;
    private String subwayStation;
    private String welfareFacility;


}
