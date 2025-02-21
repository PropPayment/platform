package com.proppay.platform.pay.adapter.in.web;

import com.proppay.platform.core.api.ApiResponse;
import com.proppay.platform.core.dto.PageRequest;
import com.proppay.platform.core.dto.PageResponse;
import com.proppay.platform.pay.adapter.in.web.dto.PropertyDetailResponse;
import com.proppay.platform.pay.adapter.in.web.dto.PropertyListResponse;
import com.proppay.platform.pay.application.in.dto.PropertyRequest;
import com.proppay.platform.pay.application.in.property.*;
import com.proppay.platform.pay.domain.property.Property;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/property")
public class PropertyApi {

    private final CreatePropertyUseCase createService;
    private final ListPropertiesUseCase listService;
    private final GetPropertyDetailsUseCase getService;
    private final UpdatePropertyUseCase updateService;
    private final DeletePropertyUseCase deleteService;

    // 매물 생성
    @PostMapping
    public ApiResponse<PropertyDetailResponse> create(@RequestBody PropertyRequest request) {
        return ApiResponse.created(PropertyDetailResponse.from(createService.create(request)));
    }

    // 최신 매물 목록 조회
    @GetMapping("/list")
    public ApiResponse<PageResponse<PropertyListResponse>> listProperty(PageRequest pageRequest) {
        Pageable pageable = org.springframework.data.domain.PageRequest.of(
                pageRequest.getPage() - 1,
                pageRequest.getSize(),
                Sort.by(Sort.Direction.DESC, "id")
        );

        Page<Property> result = listService.getList(pageable);
        List<PropertyListResponse> dtoList = PropertyListResponse.from(result.getContent());

        return ApiResponse.ok(new PageResponse<>(dtoList, pageRequest, result.getTotalElements()));
    }

    // 조회수 높은 매물 목록 조회
    @GetMapping("/list/view")
    public ApiResponse<PageResponse<PropertyListResponse>> listPropertyByView(PageRequest pageRequest) {
        Pageable pageable = org.springframework.data.domain.PageRequest.of(
                pageRequest.getPage() - 1,
                pageRequest.getSize(),
                Sort.by(Sort.Direction.DESC, "statistic.viewCount") // 조회수 기준 정렬
        );

        Page<Property> result = listService.getListByViewCount(pageable);
        List<PropertyListResponse> dtoList = PropertyListResponse.from(result.getContent());

        return ApiResponse.ok(new PageResponse<>(dtoList, pageRequest, result.getTotalElements()));
    }

    // 좋아요 많은 매물 목록 조회
    @GetMapping("/list/like")
    public ApiResponse<PageResponse<PropertyListResponse>> listPropertyByLike(PageRequest pageRequest) {
        Pageable pageable = org.springframework.data.domain.PageRequest.of(
                pageRequest.getPage() - 1,
                pageRequest.getSize(),
                Sort.by(Sort.Direction.DESC, "statistic.likeCount") // 좋아요 기준 정렬
        );

        Page<Property> result = listService.getListByLikeCount(pageable);
        List<PropertyListResponse> dtoList = PropertyListResponse.from(result.getContent());

        return ApiResponse.ok(new PageResponse<>(dtoList, pageRequest, result.getTotalElements()));
    }

    // 매물 상세 조회
    @GetMapping("/{id}")
    public ApiResponse<PropertyDetailResponse> getDetailOne(@PathVariable Long id) {
        Property property = getService.getPropertyDetails(id)
                .orElseThrow(() -> new EntityNotFoundException("매물이 존재하지 않습니다."));

        return ApiResponse.ok(PropertyDetailResponse.from(property));
    }

    // 매물 삭제
    @DeleteMapping("/{id}")
    public ApiResponse<String> delete(@PathVariable Long id) {
        deleteService.deleteProperty(id);
        return ApiResponse.ok("매물(ID: " + id + ")이 성공적으로 삭제되었습니다.");
    }

}
