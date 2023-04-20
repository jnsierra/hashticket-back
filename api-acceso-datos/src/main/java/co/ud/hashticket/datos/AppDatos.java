package co.ud.hashticket.datos;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableEurekaClient
@EnableCaching
@ComponentScan("co.ud")
public class AppDatos {

    private static final Logger LOGGER = LogManager.getLogger(AppDatos.class);

    public static void main(String... args) {
        final ConfigurableApplicationContext applicationContext = SpringApplication.run(AppDatos.class);
        LoggerFactory.getLogger(AppDatos.class).info("Los beans son {} ", applicationContext.getBeanDefinitionNames().length);
        LOGGER.info("Info level log message");
        LOGGER.debug("Debug level log message");
        LOGGER.error("Error level log message");
    }
}