package com.carboncapture.carbon.framework.interceptor;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.carboncapture.carbon.core.HttpStatus;
import com.carboncapture.carbon.framework.annotation.AccessWithoutVerification;
import com.carboncapture.carbon.framework.exception.ServiceException;
import com.carboncapture.carbon.framework.exception.UnAuthenticatedException;
import com.carboncapture.carbon.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * @author Santa Antilles
 * @description
 * @date 2024/2/29-13:47:28
 */
public class JWTInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String token = request.getHeader("token");
        if(handler instanceof HandlerMethod){
            AccessWithoutVerification annotation = ((HandlerMethod) handler).getMethodAnnotation(AccessWithoutVerification.class);
            if(annotation!=null){
                return true;
            }
        }

        String userId;
        try {
            userId = JwtUtils.verify(token);
        } catch (UnAuthenticatedException e) {
            throw new ServiceException("Time Expired!", HttpStatus.FORBIDDEN);
        }


        return true;
    }
}
