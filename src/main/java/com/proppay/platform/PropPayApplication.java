package com.proppay.platform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.proppay.platform.pay.adapter.out.jpa")
@EnableMongoRepositories(basePackages = "com.proppay.platform.pay.adapter.out.mongo")
public class PropPayApplication {

    public static void main(String[] args) {
        SpringApplication.run(PropPayApplication.class, args);
    }

}
