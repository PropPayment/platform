package com.proppay.platform.pay.application.service;

import com.proppay.platform.pay.application.in.exchange.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExchangeChatService implements StartExchangeChatUseCase, SendExchangeMessageUseCase {
}
