package co.ud.hashticket.datos.config;

import co.ud.hashticket.datos.interceptor.InterceptorRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Component
public class ConfigInterceptor extends WebMvcConfigurerAdapter {

    private final InterceptorRequest interceptorRequest;

    @Autowired
    public ConfigInterceptor(InterceptorRequest interceptorRequest) {
        this.interceptorRequest = interceptorRequest;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(interceptorRequest);
    }
}