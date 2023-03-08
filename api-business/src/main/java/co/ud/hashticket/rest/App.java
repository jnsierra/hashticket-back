package co.ud.hashticket.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("co.ud.hashticket")
@EnableEurekaClient
public class App {

    public static void main(String ... args){
        SpringApplication.run(App.class, args);
    }
}
