package com.carboncapture.carbon.framework.aspect;

import com.carboncapture.carbon.core.HttpStatus;
import com.carboncapture.carbon.framework.config.CustomHttpStatus;
import com.carboncapture.carbon.framework.exception.ServiceException;
import com.carboncapture.carbon.framework.exception.UnAuthenticatedException;
import com.carboncapture.carbon.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @author Santa Antilles
 * @description
 * @date 2024/2/29-16:55:28
 */
@Aspect
@Component
public class ProlongTokenSurvivalAspect {

    @Pointcut("@annotation(com.carboncapture.carbon.framework.annotation.EnableProlongTokenSurvival)")
    public void tokenVerification() {}

    @Around("tokenVerification()")
    public Object excludeUnAuthenticatedCases(ProceedingJoinPoint joinPoint) throws Throwable {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        HttpServletRequest request = attributes.getRequest();
        String token = request.getHeader(JwtUtils.TOKEN);
        if(token==null){
            throw new ServiceException("Unauthorized!", HttpStatus.UNAUTHORIZED);
        }
        try {
            JwtUtils.verify(token);
        } catch (UnAuthenticatedException e) {
            throw new ServiceException("Invalid token!", HttpStatus.UNAUTHORIZED);
        }
        if(!JwtUtils.isNeedUpdate(token)){
            throw new ServiceException("No need to flush", CustomHttpStatus.NO_NEED_TO_FLUSH_TOKEN);
        }
        return joinPoint.proceed();
    }
}
