package co.ud.hashticket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("co.ud.hashticket")
public class App {

    public static void main(String ... args){
        SpringApplication.run(App.class, args);
    }
}
