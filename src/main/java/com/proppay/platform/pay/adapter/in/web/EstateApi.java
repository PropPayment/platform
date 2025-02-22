package com.proppay.platform.pay.adapter.in.web;

import com.proppay.platform.core.api.ApiResponse;
import com.proppay.platform.core.dto.PageRequest;
import com.proppay.platform.core.dto.PageResponse;
import com.proppay.platform.pay.adapter.in.web.dto.EstateDetailResponse;
import com.proppay.platform.pay.adapter.in.web.dto.EstateListResponse;
import com.proppay.platform.pay.adapter.in.web.dto.EstateLocationRequest;
import com.proppay.platform.pay.application.in.estate.GetEstateUseCase;
import com.proppay.platform.pay.domain.estate.Estate;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/estate")
@RequiredArgsConstructor
public class EstateApi {

    private final GetEstateUseCase getService;

    // 상세 정보 조회
    @GetMapping
    public ApiResponse<EstateDetailResponse> getEstate(
            @RequestParam(value = "code", required = false) String code,
            @RequestParam(value = "name", required = false) String name) {

        Estate estate;

        if (code != null) {
            estate = getService.loadEstateByCode(code)
                    .orElseThrow(() -> new EntityNotFoundException("해당하는 아파트가 존재하지 않습니다."));
        } else if (name != null) {
            estate = getService.loadEstateByName(name)
                    .orElseThrow(() -> new EntityNotFoundException("해당하는 아파트가 존재하지 않습니다."));
        } else {
            throw new IllegalArgumentException("최소 하나 이상의 요청 파라미터가 필요합니다.");
        }

        return ApiResponse.created(EstateDetailResponse.from(estate));
    }

    // 특정 지역 아파트 목록 조회
    @GetMapping("/list")
    public ApiResponse<PageResponse<EstateListResponse>> getEstateByRegion(
            @RequestParam(value = "region") String region,
            PageRequest pageRequest) {

        Pageable pageable = org.springframework.data.domain.PageRequest.of(
                pageRequest.getPage() - 1,
                pageRequest.getSize(),
                Sort.by(Sort.Direction.DESC, "id")
        );

        Page<Estate> result = getService.loadEstatesByRegion(region, pageable);

        List<EstateListResponse> dtolist = EstateListResponse.from(result.getContent());

        return ApiResponse.ok(new PageResponse<>(dtolist, pageRequest, result.getTotalElements()));
    }

    // 특정 좌표 근처의 아파트 목록 조회
    @GetMapping("/list/location")
    public ApiResponse<List<EstateListResponse>> getEstateByLocation(@RequestBody EstateLocationRequest request) {

        List<Estate> list = getService.loadEstatesNearBy(request.getLongitude(), request.getLatitude(), request.getRadius());
        return ApiResponse.ok(EstateListResponse.from(list));

    }
}
