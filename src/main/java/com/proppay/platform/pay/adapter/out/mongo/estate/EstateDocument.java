package com.proppay.platform.pay.adapter.out.mongo.estate;

import com.proppay.platform.pay.domain.estate.Estate;
import com.proppay.platform.pay.domain.estate.Location;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Arrays;

@Document(collection = "db")  // "db" 컬렉션을 사용하도록 설정
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class EstateDocument {

    @Field("_id")
    private String id;

    private String kaptCode;
    private String pricePerSquareMeter;
    private String kaptAddr;
    private String kaptMparea_136;
    private String kaptMparea_135;
    private String kaptMparea_60;
    private String kaptMparea_85;
    private String kaptName;

    @GeoSpatialIndexed(type = GeoSpatialIndexType.GEO_2DSPHERE)
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

    public static EstateDocument from(Estate estate) {

        Location location = Location.builder()
                .type("Point")
                .coordinates(Arrays.asList(
                        estate.getLocation().getLongitude(),  // 경도
                        estate.getLocation().getLatitude())   // 위도
                )
                .build();

        return EstateDocument.builder()
                .kaptCode(estate.getKaptCode())
                .pricePerSquareMeter(estate.getPricePerSquareMeter())
                .kaptAddr(estate.getKaptAddr())
                .kaptMparea_135(estate.getKaptMparea_135())
                .kaptMparea_136(estate.getKaptMparea_136())
                .kaptMparea_60(estate.getKaptMparea_60())
                .kaptMparea_85(estate.getKaptMparea_85())
                .kaptName(estate.getKaptName())
                .location(location)
                .convenientFacility(estate.getConvenientFacility())
                .educationFacility(estate.getEducationFacility())
                .kaptdPcnt(estate.getKaptdPcnt())
                .kaptdPcntu(estate.getKaptdPcntu())
                .kaptdWtimebus(estate.getKaptdWtimebus())
                .kaptdWtimesub(estate.getKaptdWtimesub())
                .subwayLine(estate.getSubwayLine())
                .subwayStation(estate.getSubwayStation())
                .welfareFacility(estate.getWelfareFacility())
                .build();
    }

    public Estate toDomain() {

        Location location = Location.builder()
                .type("Point")
                .coordinates(Arrays.asList(
                        this.getLocation().getLongitude(),  // 경도
                        this.getLocation().getLatitude())   // 위도
                )
                .build();

        return Estate.builder()
                .id(this.id)
                .kaptCode(this.kaptCode)
                .pricePerSquareMeter(this.pricePerSquareMeter)
                .kaptAddr(this.kaptAddr)
                .kaptMparea_135(this.kaptMparea_135)
                .kaptMparea_136(this.kaptMparea_136)
                .kaptMparea_60(this.kaptMparea_60)
                .kaptMparea_85(this.kaptMparea_85)
                .kaptName(this.kaptName)
                .location(location)
                .convenientFacility(this.convenientFacility)
                .educationFacility(this.educationFacility)
                .kaptdPcnt(this.kaptdPcnt)
                .kaptdPcntu(this.kaptdPcntu)
                .kaptdWtimebus(this.kaptdWtimebus)
                .kaptdWtimesub(this.kaptdWtimesub)
                .subwayLine(this.subwayLine)
                .subwayStation(this.subwayStation)
                .welfareFacility(this.welfareFacility)
                .build();
    }

}
