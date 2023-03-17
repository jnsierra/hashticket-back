package co.ud.hashticket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class AppPublicUsers {
    public static void main(String[] args) {
        SpringApplication.run(AppPublicUsers.class, args);
    }
}