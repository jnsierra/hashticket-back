package co.ud.hashticket.pub.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;
import java.util.function.Function;

@Slf4j
public class TraceLogInterceptor implements HandlerInterceptor {
    private final Function<HttpServletRequest, Boolean> funcValidExistsHeader = (request) -> Objects.isNull(request.getHeader("x-uow"));
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