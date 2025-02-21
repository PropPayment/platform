package com.proppay.platform.core.config;

import com.proppay.platform.pay.application.in.dto.UserRequest;
import com.proppay.platform.pay.application.in.user.RegisterUserUseCase;
import com.proppay.platform.pay.application.out.LoadUserPort;
import com.proppay.platform.pay.domain.user.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class DataInitializer {

    private final RegisterUserUseCase registerService;
    private final LoadUserPort loadService;


    @Bean
    public CommandLineRunner initDatabase() {
        return args -> {
            if (loadService.loadAllUsers() == 0) { // 데이터가 없으면 초기화
                UserRequest userRequest = new UserRequest();
                userRequest.setEmail("admin@proppay.com");
                userRequest.setUsername("admin");
                userRequest.setPassword("admin");
                userRequest.setPhoneNumber("123-4567-8900");

                User user = registerService.registerUser(userRequest);

                log.info("✅ 기본 사용자 생성 완료: {} ", user.getId());
            }
        };
    }
}
