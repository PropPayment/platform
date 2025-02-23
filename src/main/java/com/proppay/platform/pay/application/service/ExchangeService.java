package com.proppay.platform.pay.application.service;

import com.proppay.platform.pay.adapter.in.web.dto.ExchangeDetailResponse;
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
    public ExchangeDetailResponse requestExchange(ExchangeRequest request) {
        User buyer = getUser(request.getBuyerId());
        User seller = getUser(request.getSellerId());

        Property property = loadPropertyPort.loadPropertyBySellerIdAndPropertyId(seller.getId(), request.getPropertyId())
                .orElseThrow(() -> new IllegalStateException("판매자가 내놓은 해당 매물을 찾을 수 없습니다."));

        ExchangeSnippet snippet = ExchangeSnippet.of(request.getRequestedAt(), request.getNote());
        Exchange exchange = savePort.saveExchange(Exchange.of(seller.getId(), buyer.getId(), property.getId(), snippet));

        return ExchangeDetailResponse.from(exchange, seller, buyer);
    }

    @Override
    public void rejectExchange(Long exchangeId) {
        if (!loadPort.existsExchange(exchangeId)) {
            throw new EntityNotFoundException("거래 요청이 존재하지 않습니다.");
        }
        deletePort.deleteExchange(exchangeId);
    }

    @Override
    public ExchangeDetailResponse approveExchange(Long exchangeId, Long lawyerId) {
        return updateExchangeStatus(exchangeId, ExchangeStatus.MATCHED, lawyerId);
    }

    @Override
    public ExchangeDetailResponse cancelExchange(Long exchangeId) {
        return updateExchangeStatus(exchangeId, ExchangeStatus.CANCELED, null);
    }

    @Override
    public ExchangeDetailResponse completeExchange(Long exchangeId) {
        return updateExchangeStatus(exchangeId, ExchangeStatus.CONTRACTED, null);
    }

    /**
     *   상태 업데이트 공통 메서드
     * - 승인(approve), 취소(cancel), 완료(complete) 처리 가능
     * - 법무사가 필요한 경우(lawyerId != null)에만 조회
     */
    private ExchangeDetailResponse updateExchangeStatus(Long exchangeId, ExchangeStatus status, Long lawyerId) {
        Exchange exchange = updatePort.updateExchangeStatus(exchangeId, status)
                .orElseThrow(() -> new EntityNotFoundException("해당 거래 요청을 찾을 수 없습니다."));

        User buyer = getUser(exchange.getBuyerId());
        User seller = getUser(exchange.getSellerId());

        if (lawyerId != null) {
            Lawyer lawyer = loadLawyerPort.loadLawyer(lawyerId)
                    .orElseThrow(() -> new EntityNotFoundException("해당하는 법무사가 존재하지 않습니다."));

            // 변호사 등록 또는 변경 처리
            if (exchange.getLawyerId() == null) {
                exchange.addLawyer(lawyerId);
            } else {
                exchange.changeLawyer(lawyerId);
            }

            // 업데이트된 거래 정보 저장
            exchange = savePort.saveExchange(exchange);

            return ExchangeDetailResponse.from(exchange, seller, buyer, lawyer);
        }

        return ExchangeDetailResponse.from(exchange, seller, buyer);
    }


    private User getUser(Long id) {
        return loadUserPort.loadUser(id)
                .orElseThrow(() -> new EntityNotFoundException("해당하는 유저가 존재하지 않습니다."));
    }

}

