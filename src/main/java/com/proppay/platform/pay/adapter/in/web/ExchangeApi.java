package com.proppay.platform.pay.adapter.in.web;

import com.proppay.platform.core.api.ApiResponse;
import com.proppay.platform.pay.adapter.in.web.dto.ExchangeDetailResponse;
import com.proppay.platform.pay.adapter.in.web.dto.ExchangeRequest;
import com.proppay.platform.pay.adapter.in.web.dto.ExchangeUpdateRequest;
import com.proppay.platform.pay.application.in.exchange.ContractExchangeUseCase;
import com.proppay.platform.pay.application.in.exchange.RejectExchangeUseCase;
import com.proppay.platform.pay.application.in.exchange.RequestExchangeUseCase;
import com.proppay.platform.pay.application.out.exchange.UpdateExchangePort;
import com.proppay.platform.pay.domain.exchange.Exchange;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/exchange")
@RequiredArgsConstructor
public class ExchangeApi {

    private final RequestExchangeUseCase requestService;
    private final RejectExchangeUseCase rejectService;
    private final UpdateExchangePort updateService;

    // 추후, 법무사에서 관리할 내용
    private final ContractExchangeUseCase contractService;

    // 교환 요청하기
    @PostMapping()
    public ApiResponse<ExchangeDetailResponse> request(@RequestBody ExchangeRequest request) {

        return ApiResponse.created(ExchangeDetailResponse.from(requestService.requestExchange(request)));
    }

    // 교환 거절하기 -> 바로 삭제, 새로 요청해야한다.
    @DeleteMapping("/{id}")
    public ApiResponse<String> reject(@PathVariable Long id) {
        rejectService.rejectExchange(id);
        return ApiResponse.created("거래가 취소되었습니다.");
    }

    @PutMapping()
    public ApiResponse<ExchangeDetailResponse> update(@RequestBody ExchangeUpdateRequest request) {
        Exchange exchange = updateService.updateExchangeStatus(request.getExchangeId(), request.getStatus())
                .orElseThrow(() -> new EntityNotFoundException("거래가 존재하지 않습니다."));

        return ApiResponse.created(ExchangeDetailResponse.from(exchange));
    }
}
