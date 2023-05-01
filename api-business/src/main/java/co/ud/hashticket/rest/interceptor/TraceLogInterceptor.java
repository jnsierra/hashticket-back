package co.ud.hashticket.rest.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class TraceLogInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        request.setAttribute("start" , System.currentTimeMillis());
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