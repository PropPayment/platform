package com.proppay.platform.pay.adapter.in.web;

import com.proppay.platform.core.api.ApiResponse;
import com.proppay.platform.pay.adapter.in.web.dto.ExchangeDetailResponse;
import com.proppay.platform.pay.adapter.in.web.dto.ExchangeRequest;
import com.proppay.platform.pay.adapter.in.web.dto.ExchangeUpdateRequest;
import com.proppay.platform.pay.application.in.exchange.ContractExchangeUseCase;
import com.proppay.platform.pay.application.in.exchange.RejectExchangeUseCase;
import com.proppay.platform.pay.application.in.exchange.RequestExchangeUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/exchange")
@RequiredArgsConstructor
public class ExchangeApi {

    private final RequestExchangeUseCase requestService;
    private final RejectExchangeUseCase rejectService;
    private final ContractExchangeUseCase contractService;

    // 교환 요청하기
    @PostMapping()
    public ApiResponse<ExchangeDetailResponse> request(@RequestBody ExchangeRequest request) {

        return ApiResponse.created(requestService.requestExchange(request));
    }

    // 교환 거절하기 -> 바로 삭제, 새로 요청해야한다.
    @DeleteMapping("/{id}")
    public ApiResponse<String> reject(@PathVariable Long id) {
        rejectService.rejectExchange(id);
        return ApiResponse.created("요청이 취소되었습니다.");
    }

    @PutMapping("/approve")
    public ApiResponse<ExchangeDetailResponse> approve(@RequestBody ExchangeUpdateRequest request) {
        return ApiResponse.created(contractService.approveExchange(request.getExchangeId(), request.getLawyerId()));
    }

    @PutMapping("/complete")
    public ApiResponse<ExchangeDetailResponse> complete(@RequestBody ExchangeUpdateRequest request) {
        return ApiResponse.created(contractService.completeExchange(request.getExchangeId()));
    }

    @PutMapping("/cancel")
    public ApiResponse<ExchangeDetailResponse> cancel(@RequestBody ExchangeUpdateRequest request) {
        return ApiResponse.created(contractService.cancelExchange(request.getExchangeId()));
    }


}
