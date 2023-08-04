package com.chunkie.pmp_server.interceptor;

import com.chunkie.pmp_server.annotation.Idempotent;
import com.chunkie.pmp_server.exception.IdempotentException;
import com.chunkie.pmp_server.service.IdempotentService;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

public class IdempotentInterceptor implements HandlerInterceptor {

    @Resource
    private IdempotentService idempotentService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (!(handler instanceof HandlerMethod)) return true;

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        if (method.isAnnotationPresent(Idempotent.class)) {
            String keyPrefix = ((HandlerMethod) handler).getMethodAnnotation(Idempotent.class).prefix();
            String token = request.getHeader("Idem-Token");
            if (idempotentService.validateToken(keyPrefix, token)){
                throw new IdempotentException();
            }
        }else return true;
        return true;
    }

}
