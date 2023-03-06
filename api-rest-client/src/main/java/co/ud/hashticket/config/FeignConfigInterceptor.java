package co.ud.hashticket.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class FeignConfigInterceptor implements RequestInterceptor {
    private final String AUTHORIZATION_HEADER_NAME= "authorization";
    @Autowired
    private HttpServletRequest request;
    @Override
    public void apply(RequestTemplate requestTemplate) {
        String token = request.getHeader(AUTHORIZATION_HEADER_NAME);
        requestTemplate.header(AUTHORIZATION_HEADER_NAME,token);
    }
}
