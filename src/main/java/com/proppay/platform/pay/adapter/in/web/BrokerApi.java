package com.proppay.platform.pay.adapter.in.web;

import com.proppay.platform.core.api.ApiResponse;
import com.proppay.platform.core.dto.PageRequest;
import com.proppay.platform.core.dto.PageResponse;
import com.proppay.platform.pay.adapter.in.web.dto.*;
import com.proppay.platform.pay.application.in.broker.*;
import com.proppay.platform.pay.domain.broker.Broker;
import com.proppay.platform.pay.domain.broker.BrokerStatus;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/v1/broker")
@RequiredArgsConstructor
public class BrokerApi {

    private final RegisterBrokerUseCase registerService;
    private final DeleteBrokerUseCase deleteService;
    private final GetBrokerUseCase getService;
    private final ListBrokersUseCase listService;
    private final ApproveBrokerUseCase approveService;

    @PostMapping()
    public ApiResponse<BrokerDetailResponse> create(@Valid @RequestBody BrokerRequest request) {

        return ApiResponse.created(BrokerDetailResponse.from(registerService.registerBroker(request)));
    }

    @GetMapping("/list")
    public ApiResponse<PageResponse<BrokerListResponse>> list(
            PageRequest pageRequest,
            @RequestParam(value = "status", required = false) BrokerStatus status) {

        Pageable pageable = org.springframework.data.domain.PageRequest.of(
                pageRequest.getPage() - 1,
                pageRequest.getSize(),
                Sort.by(Sort.Direction.DESC, "id")
        );

        BrokerStatus safeStatus = Optional.ofNullable(status).orElse(BrokerStatus.APPROVED);

        Page<Broker> result;
        switch (safeStatus) {
            case PENDING:
                result = listService.getListNotApproved(pageable);
                break;
            case REJECTED:
                result = listService.getListRejected(pageable);
                break;
            case APPROVED:
            default:
                result = listService.getList(pageable);
                break;
        }

        List<BrokerListResponse> dtoList = BrokerListResponse.from(result.getContent());

        return ApiResponse.ok(new PageResponse<>(dtoList, pageRequest, result.getTotalElements()));
    }

    @GetMapping("/{id}")
    public ApiResponse<BrokerDetailResponse> get(@PathVariable("id") Long id) {

        Broker broker = getService.getBroker(id)
                .orElseThrow(() -> new EntityNotFoundException("해당 공인중개사가 존재하지 않습니다."));

        return ApiResponse.ok(BrokerDetailResponse.from(broker));
    }

    @PostMapping("/approve")
    public ApiResponse<String> createBroker(@RequestBody ApproveRequest request) {
        approveService.approveBroker(request.getAdminId(), request.getBrokerId());
        return ApiResponse.created("성공적으로 승인되었습니다.");
    }
}
