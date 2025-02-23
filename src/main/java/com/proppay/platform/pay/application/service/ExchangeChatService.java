package com.proppay.platform.pay.application.service;

import com.proppay.platform.pay.application.in.exchange.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class ExchangeChatService implements StartExchangeChatUseCase, SendExchangeMessageUseCase {
}
