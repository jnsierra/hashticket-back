package co.ud.hashticket.pub.config;

import co.ud.hashticket.pub.interceptor.TraceLogInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        System.out.println("Llego");
        registry.addInterceptor( new TraceLogInterceptor());
    }

}
