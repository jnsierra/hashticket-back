package co.ud.hashticket.config;

import feign.Logger;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@EnableFeignClients(basePackages = "co.ud.hashticket.client")
public class FeignConfig {
    public FeignConfig() {
        System.out.println("Llega al configurador");
    }

    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
}