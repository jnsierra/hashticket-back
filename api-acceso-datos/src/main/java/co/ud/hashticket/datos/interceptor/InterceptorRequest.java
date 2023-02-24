package co.ud.hashticket.datos.interceptor;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Component
public class InterceptorRequest implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            Object principal = authentication.getPrincipal();
            System.out.println(principal.getClass());
            if(principal instanceof Jwt){
                Jwt jwt = (Jwt) principal;
                Map<String, Object> claims = jwt.getClaims();
                System.out.println(claims.get("cognito:groups"));
            }
        }
        return true;
    }

}
