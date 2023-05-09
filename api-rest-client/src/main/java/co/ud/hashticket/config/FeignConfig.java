package co.ud.hashticket.config;

import feign.Logger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@EnableFeignClients(basePackages = "co.ud.hashticket.client")
@Slf4j
public class FeignConfig {
    public FeignConfig() {
        log.info("Configure Feign clients");
    }

    @Bean
    Logger.Level feignLoggerLevel() {
        System.out.println("Llegooooo");
        return Logger.Level.FULL;
    }
}