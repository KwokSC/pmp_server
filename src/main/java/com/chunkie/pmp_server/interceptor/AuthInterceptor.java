package com.chunkie.pmp_server.interceptor;

import com.chunkie.pmp_server.annotation.LoginRequired;
import com.chunkie.pmp_server.exception.UnauthorizedException;
import com.chunkie.pmp_server.service.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(AuthInterceptor.class);
    @Resource
    private AuthService authService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        logger.info("The Interceptor was called");
        if (!(handler instanceof HandlerMethod)) return true;
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        if (handlerMethod.getMethodAnnotation(LoginRequired.class)!=null) {
            String authToken = request.getHeader("Authorization");
            if (!authService.getUserByToken(authToken).isEmpty()) {
                return true;
            } else {
                throw new UnauthorizedException();
            }
        }else return true;
    }
}
