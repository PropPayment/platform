package com.proppay.platform.pay.application.service;

import com.proppay.platform.pay.adapter.in.web.dto.ExchangeRequest;
import com.proppay.platform.pay.application.in.exchange.ContractExchangeUseCase;
import com.proppay.platform.pay.application.in.exchange.RejectExchangeUseCase;
import com.proppay.platform.pay.application.in.exchange.RequestExchangeUseCase;
import com.proppay.platform.pay.application.out.exchange.DeleteExchangePort;
import com.proppay.platform.pay.application.out.exchange.LoadExchangePort;
import com.proppay.platform.pay.application.out.exchange.SaveExchangePort;
import com.proppay.platform.pay.application.out.exchange.UpdateExchangePort;
import com.proppay.platform.pay.application.out.lawyer.LoadLawyerPort;
import com.proppay.platform.pay.application.out.property.LoadPropertyPort;
import com.proppay.platform.pay.application.out.user.LoadUserPort;
import com.proppay.platform.pay.domain.exchange.Exchange;
import com.proppay.platform.pay.domain.exchange.ExchangeSnippet;
import com.proppay.platform.pay.domain.exchange.ExchangeStatus;
import com.proppay.platform.pay.domain.lawyer.Lawyer;
import com.proppay.platform.pay.domain.property.Property;
import com.proppay.platform.pay.domain.user.User;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ExchangeService implements ContractExchangeUseCase, RequestExchangeUseCase, RejectExchangeUseCase {

    private final SaveExchangePort savePort;
    private final LoadExchangePort loadPort;
    private final DeleteExchangePort deletePort;
    private final UpdateExchangePort updatePort;

    private final LoadUserPort loadUserPort;
    private final LoadLawyerPort loadLawyerPort;
    private final LoadPropertyPort loadPropertyPort;

    @Override
    public Exchange requestExchange(ExchangeRequest request) {
        // 유저 정보
        User buyer = getUser(request.getBuyerId());
        User seller = getUser(request.getSellerId());

        // 기본 법무사 선임
        Lawyer lawyer = loadLawyerPort.loadLawyer(request.getLawyerId())
                .orElseThrow(() -> new EntityNotFoundException("해당하는 법무사가 존재하지 않습니다."));

        // 매물 정보 가져오기
        Property property = loadPropertyPort.loadPropertyBySellerIdAndPropertyId(seller.getId(), request.getPropertyId())
                .orElseThrow(() -> new IllegalStateException("판매자가 내놓은 해당 매물을 찾을 수 없습니다."));

        // 교환 정보 생성
        ExchangeSnippet snippet = ExchangeSnippet.of(request.getRequestedAt(), request.getNote());

        Exchange exchange = Exchange.of(seller, buyer, property, lawyer, snippet);

        return savePort.saveExchange(exchange);
    }


    private User getUser(Long id) {
        return loadUserPort.loadUser(id)
                .orElseThrow(() -> new EntityNotFoundException("해당하는 유저가 존재하지 않습니다."));
    }

    @Override
    public void rejectExchange(Long exchangeId) {
        if (!loadPort.existsExchange(exchangeId)) {
            throw new EntityNotFoundException("거래 요청이 존재하지 않습니다.");
        }
        deletePort.deleteExchange(exchangeId);
    }


    @Override
    public Exchange approveExchange(Long exchangeId) {
        return updatePort.updateExchangeStatus(exchangeId, ExchangeStatus.MATCHED)
                .orElseThrow(() -> new EntityNotFoundException("해당 거래 요청을 찾을 수 없습니다."));
    }

    @Override
    public Exchange cancelExchange(Long exchangeId) {
        return updatePort.updateExchangeStatus(exchangeId, ExchangeStatus.CANCELED)
                .orElseThrow(() -> new EntityNotFoundException("해당 거래 요청을 찾을 수 없습니다."));
    }

    @Override
    public Exchange completeExchange(Long exchangeId) {
        return updatePort.updateExchangeStatus(exchangeId, ExchangeStatus.CONTRACTED)
                .orElseThrow(() -> new EntityNotFoundException("해당 거래 요청을 찾을 수 없습니다."));
    }


}
