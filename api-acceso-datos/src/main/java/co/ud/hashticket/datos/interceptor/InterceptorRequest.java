package co.ud.hashticket.datos.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@Slf4j
public class InterceptorRequest implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        request.setAttribute("start" , System.currentTimeMillis());
        log.info("This is the user authenticated: {} ", authentication);
        return true;
    }
    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response,
                                Object handler,
                                Exception exception) throws Exception {

        log.info("PERFORMANCE|{}|{}|{}"
                ,response.getStatus()
                ,System.currentTimeMillis() - (long) request.getAttribute("start")
                ,request.getRequestURI());
    }

}
