package com.carboncapture.carbon.utils;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.carboncapture.carbon.core.HttpStatus;
import com.carboncapture.carbon.framework.exception.ServiceException;
import com.carboncapture.carbon.framework.exception.UnAuthenticatedException;

import java.util.Date;

public class JwtUtils {

    /**
     * 过期时间，120分钟
     */
    private static final long EXPIRE_TIME = 120 * 60 * 1000;
    private static final String TOKEN_SECRET = "@#2nd8s$%#$%0oO.+";  //密钥
    public static final String TOKEN = "token";

    /**
     * 签名生成
     * @param userName 用户名
     * @return token
     */
    public static String sign(String userName) {
        return JWT.create()
                .withSubject(userName)
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRE_TIME))
                // 使用了HMAC256加密算法。
                .sign(Algorithm.HMAC256(TOKEN_SECRET));
    }

    /**
     * 签名验证
     *
     * @param token token
     * @return userName
     */
    public static String verify(String token) throws UnAuthenticatedException {
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(TOKEN_SECRET))
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            if (jwt != null) {
                return jwt.getSubject();
            } else {
                return null;
            }
        } catch (TokenExpiredException e){
            throw new UnAuthenticatedException("Time expired");
        } catch (Exception e) {
            throw new ServiceException("Token error", HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * 检查 token 是否需要更新
     *
     * @param token token 值
     * @return 过期时间
     */
    public static boolean isNeedUpdate(String token) {
        // 获取 token 过期时间
        try {
            Date expiresAt = JWT.require(Algorithm.HMAC256(TOKEN_SECRET))
                    .build()
                    .verify(token)
                    .getExpiresAt();
            // 时间过去一半之后更新token
            return (expiresAt.getTime() - System.currentTimeMillis()) < (EXPIRE_TIME >> 1);
        } catch (TokenExpiredException e) {
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
